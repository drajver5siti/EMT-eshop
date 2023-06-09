import { useEffect, useState } from "react";
import useFetch from "../hooks/useFetch";
import BookService from "../services/BookService";
import { categoryOptions, CategoryType } from "../types";
import { AuthorType } from "../types/authors";
import { BookType } from "../types/books";

type AddBookType = {
    name: BookType['name'],
    availableCopies?: BookType['availableCopies'],
    author?: AuthorType['id'],
    category?: CategoryType
}

const initState: AddBookType = {
    name: "",
    availableCopies: undefined,
    author: undefined,
    category: categoryOptions.at(0)
}

const AddBook: React.FC = () => {

    const { data } = useFetch<AuthorType[]>('/api/authors');

    const [book, setBook] = useState(initState);

    useEffect(() => {
        setBook((prev) => ({ ...prev, author: data?.at(0)?.id }))
    }, [data]);

    const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();

        if (book.availableCopies && book.author && book.category) {
            await BookService.add({
                name: book.name,
                author: book.author,
                category: book.category,
                availableCopies: book.availableCopies
            });

            setBook(initState)
        }
    }

    return (
        <div className="flex justify-center">
            <form className="flex flex-col gap-y-2" onSubmit={handleSubmit}>
                <label className="flex flex-col font-semibold text-sm">
                    Name:
                    <input
                        type="text"
                        className="border border-black outline-none py-1 px-1 rounded-sm"
                        value={book.name}
                        onChange={(e) => setBook((prev) => ({ ...prev, name: e.target.value }))}
                    />
                </label>
                <label className="flex flex-col font-semibold text-sm">
                    Category:
                    <select
                        className="border border-black rounded-sm outline-none py-1 px-1"
                        value={book.category}
                        onChange={(e) => setBook((prev) => ({ ...prev, category: e.target.value as CategoryType }))}
                    >
                        {categoryOptions.map(cat => <option key={cat}>{cat}</option>)}
                    </select>
                </label>
                <label className="flex flex-col font-semibold text-sm">
                    Author:
                    <select
                        value={book.author ?? ""}
                        className="border border-black rounded-sm outline-none py-1 px-1"
                        onChange={(e) => setBook((prev) => ({ ...prev, author: parseInt(e.target.value) }))}
                    >
                        <option style={{ display: 'none' }}></option>
                        {data?.map(author => <option key={author.id} value={author.id}>{author.name} {author.surname}</option>)}
                    </select>
                </label>
                <label className="flex flex-col font-semibold text-sm">
                    Copies:
                    <input
                        type="number"
                        className="border border-black outline-none py-1 px-1 rounded-sm"
                        value={book.availableCopies || ''}
                        onChange={(e) => setBook((prev) => ({ ...prev, availableCopies: parseInt(e.target.value) }))}
                    />
                </label>
                <button type="submit">Create</button>
            </form>
        </div>
    )
}

export { AddBook };
export default AddBook;