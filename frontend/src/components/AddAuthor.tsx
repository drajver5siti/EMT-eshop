import { useEffect, useState } from "react";
import { AddAuthorType } from "../types/authors";
import { CountryType } from "../types/countries";
import useFetch from "../hooks/useFetch";
import AuthorService from "../services/AuthorService";

const initState: AddAuthorType = {
    name: "",
    surname: "",
    country: undefined
}

const AddAuthor: React.FC = () => {

    const { data } = useFetch<CountryType[]>('/api/countries');

    const [author, setAuthor] = useState(initState)

    useEffect(() => {
        setAuthor((prev) => ({ ...prev, country: data?.at(0)?.id }))
    }, [data]);

    const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        if (author.country) {
            await AuthorService.add({
                name: author.name,
                surname: author.surname,
                country: author.country
            });

            setAuthor(initState);
        }
    }

    return (
        <div className="flex justify-center">
            <form className="flex flex-col gap-y-2" onSubmit={handleSubmit}>
                <label className="flex flex-col font-semibold text-sm">
                    Name:
                    <input
                        type="text"
                        required
                        className="border border-black outline-none py-1 px-1 rounded-sm"
                        value={author.name}
                        onChange={(e) => setAuthor((prev) => ({ ...prev, name: e.target.value }))}
                    />
                </label>
                <label className="flex flex-col font-semibold text-sm">
                    Surname:
                    <input
                        type="text"
                        required
                        className="border border-black outline-none py-1 px-1 rounded-sm"
                        value={author.surname}
                        onChange={(e) => setAuthor((prev) => ({ ...prev, surname: e.target.value }))}
                    />
                </label>
                <label className="flex flex-col font-semibold text-sm">
                    Country:
                    <select
                        value={author.country}
                        required
                        className="border border-black rounded-sm outline-none py-1 px-1"
                        onChange={(e) => setAuthor(prev => ({ ...prev, country: parseInt(e.target.value) }))}
                    >
                        {data?.map((country) => <option key={country.id} value={country.id}>{country.name}</option>)}
                    </select>
                </label>
                <button type="submit">Create</button>
            </form>
        </div>
    )
}

export { AddAuthor };
export default AddAuthor;