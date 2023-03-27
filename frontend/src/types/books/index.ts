import { CategoryType } from ".."
import { AuthorType } from "../authors"

export type BookType = {
    id: number,
    name: string,
    category: CategoryType,
    author: AuthorType,
    availableCopies: number
}

export type AddBookType = {
    name: string,
    category: CategoryType,
    author: AuthorType['id'],
    availableCopies: number
}