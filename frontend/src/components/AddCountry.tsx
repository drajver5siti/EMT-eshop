import { useState } from "react"
import CountryService from "../services/CountryService";

type AddCountryType = {
    name: string,
    continent: string
}

const initState: AddCountryType = {
    name: "",
    continent: ""
}

const AddCountry = () => {

    const [country, setCountry] = useState(initState);

    const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        await CountryService.add(country);

        setCountry(initState);
    }

    return (
        <div className="flex justify-center">
            <form className="flex flex-col gap-y-2" onSubmit={handleSubmit}>
                <label className="flex flex-col font-semibold text-sm">
                    Name:
                    <input
                        type="text"
                        className="border border-black outline-none py-1 px-1 rounded-sm"
                        value={country.name}
                        onChange={(e) => setCountry((prev) => ({ ...prev, name: e.target.value }))}
                    />
                </label>
                <label className="flex flex-col font-semibold text-sm">
                    Continent:
                    <input
                        type="text"
                        className="border border-black outline-none py-1 px-1 rounded-sm"
                        value={country.continent}
                        onChange={(e) => setCountry((prev) => ({ ...prev, continent: e.target.value }))}
                    />
                </label>
                <button type="submit">Create</button>
            </form >
        </div >
    )
}

export { AddCountry };
export default AddCountry;