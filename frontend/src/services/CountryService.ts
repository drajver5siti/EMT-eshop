import { AddCountryType } from "../types/countries";

const API = "http://localhost:9090/api/countries";

const headers = {
    'Content-Type': 'application/json'
}

type CountryServiceType = {
    add: (data: AddCountryType) => Promise<Response>
}

const CountryService: CountryServiceType = {
    add: async (data) => await fetch((API), { method: 'POST', body: JSON.stringify(data), headers: headers})
}

export {CountryService};
export default CountryService;