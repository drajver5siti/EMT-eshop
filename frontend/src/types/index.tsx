export type BookType = {
    id: number,
    name: string,
    category: CategoryType,
    author: AuthorType,
    availableCopies: number
}

export type AuthorType = {
    id: number,
    name: string,
    surname: string,
    country: CountryType
}

export type CountryType = {
    id: number,
    name: string,
    continent: string
}

export type CategoryType = "NOVEL" | "THRILER" | "HISTORY" | "FANTASY" | "BIOGRAPHY" | "CLASSICS" | "DRAMA";
export const categoryOptions: CategoryType[] = ["NOVEL", "THRILER", "HISTORY", "FANTASY", "BIOGRAPHY", "CLASSICS", "DRAMA"];
