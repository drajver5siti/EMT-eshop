import { useEffect, useState } from "react";

type SearchFunction = (signal: AbortSignal) => Promise<Response>;

function useFetch<T>(searchFn: SearchFunction) {

    const [error, setError] = useState(null);
    const [loading, setLoading] = useState(false);
    const [response, setResponse] = useState<T | null>(null);


    useEffect(() => {
        let mounted = true;
        const controller = new AbortController();

        const fetchData = async () => {

            try {
                const res = await searchFn(controller.signal);
                console.log(res);
            }
            catch (err) {
            }
        }

        fetchData();

        return () => {
            mounted = false;
            controller.abort();
        }

    }, []);

    return {
        error,
        loading,
        response
    }
}


export { useFetch };
export default useFetch;