import { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import useFetch from '../hooks/useFetch';
import BookService from '../services/BookService';
import { categoryOptions, CategoryType } from '../types';
import { AuthorType } from '../types/authors';
import { AddBookType, BookType } from '../types/books';

type EditBookType = AddBookType;

const EditBook = () => {
    const { id } = useParams();

    const book = useFetch<BookType>('/api/books/' + id);
    const authors = useFetch<AuthorType[]>('/api/authors');

    const [editBook, setEditBook] = useState({} as EditBookType);

    useEffect(() => {
        if (book.data) {
            setEditBook({
                ...book.data,
                author: book.data.author.id
            });
        }
    }, [book.data]);


    const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();

        await BookService.edit(parseInt(id as string), editBook)
    }

    if (book.loading) {
        return <p>Loading...</p>
    }

    return (
        <div className='flex justify-center'>
            <form className='flex flex-col gap-y-2' onSubmit={handleSubmit}>
                <label className="flex flex-col font-semibold text-sm">
                    Name:
                    <input
                        type="text"
                        required
                        className="border border-black outline-none py-1 px-1 rounded-sm"
                        value={editBook.name}
                        onChange={(e) => setEditBook((prev) => ({ ...prev, name: e.target.value }))}
                    />
                </label>
                <label className="flex flex-col font-semibold text-sm">
                    Category:
                    <select
                        className="border border-black rounded-sm outline-none py-1 px-1"
                        required
                        value={editBook.category}
                        onChange={(e) => setEditBook((prev) => ({ ...prev, category: e.target.value as CategoryType }))}
                    >
                        {categoryOptions.map(cat => <option key={cat}>{cat}</option>)}
                    </select>
                </label>
                <label className="flex flex-col font-semibold text-sm">
                    Author:
                    <select
                        value={editBook.author}
                        required
                        className="border border-black rounded-sm outline-none py-1 px-1"
                        onChange={(e) => setEditBook((prev) => ({ ...prev, author: parseInt(e.target.value) }))}
                    >
                        {authors.data?.map(author => <option key={author.id} value={author.id}>{author.name} {author.surname}</option>)}
                    </select>
                </label>
                <label className="flex flex-col font-semibold text-sm">
                    Copies:
                    <input
                        type="number"
                        required
                        className="border border-black outline-none py-1 px-1 rounded-sm"
                        value={editBook?.availableCopies || ''}
                        onChange={(e) => setEditBook((prev) => ({ ...prev, availableCopies: parseInt(e.target.value) }))}
                    />
                </label>
                <button type="submit">Update</button>
            </form>
        </div>
    )
}

export { EditBook };
export default EditBook;