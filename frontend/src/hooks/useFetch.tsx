import { useEffect, useState } from "react";

type URL = string;

type FetchError = {
    status: number,
    error: string,
}

type FetchResult<T> = {
    data: T | null,
    error: FetchError | null,
    loading: boolean,
    refetch: (query?: Query) => void
}

type Query = {
    page: number,
    size: number
}

const headers = {
    'Content-Type': 'application/json',
    'Access-Control-Allow-Origin': '*',
}

function useFetch<T>(url: URL, initQuery?: Query): FetchResult<T> {

    const [trigger, setTrigger] = useState({});
    const [data, setData] = useState<T | null>(null);
    const [error, setError] = useState<FetchError | null>(null);
    const [loading, setLoading] = useState(false);
    const [query, setQuery] = useState(initQuery);


    useEffect(() => {
        let mounted = true;
        const controller = new AbortController();

        const fetchData = () => {

            setLoading(true);
            let api = "http://localhost:9090" + url;
            if (query) {
                api = api + "?page=" + query.page + "&size=" + query.size;
            }
            fetch(api, { signal: controller.signal, headers: headers })
                .then(res => res.json())
                .then(res => {
                    if (mounted) {
                        setError(null);
                        setData(res);
                    }
                })
                .catch(err => {
                    if (controller.signal.aborted) return;
                    if (mounted) {
                        setError(err);
                        setData(null);
                    }
                })
                .finally(() => {
                    if (mounted) {
                        setLoading(false);
                    }
                })
        }

        fetchData();

        return () => {
            mounted = false;
            controller.abort();
        }

    }, [trigger, query]);

    return {
        error,
        loading,
        data,
        refetch: (query) => {
            if (query) {
                setQuery(query);
            }
            else {
                setTrigger({})
            }
        }
    }
}


export { useFetch };
export default useFetch;