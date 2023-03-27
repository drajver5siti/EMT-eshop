import { CountryType } from "../countries"

export type AuthorType = {
    id: number,
    name: string,
    surname: string,
    country: CountryType
}

export type AddAuthorType = {
    name: string,
    surname: string,
    country?: CountryType['id']
}