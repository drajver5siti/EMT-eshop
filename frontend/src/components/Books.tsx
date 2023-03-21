import React from 'react';
import { Link, useNavigate } from 'react-router-dom';
import useFetch from '../hooks/useFetch';
import BookService from '../services/BookService';
import { BookType } from '../types';
import Book from './Book';

const Books = () => {

    const navigate = useNavigate();
    const { loading, error, data, refetch } = useFetch<BookType[]>('/api/books');

    const handleEdit = (id: BookType['id']) => {
        navigate('/books/' + id);
    }

    const handleTake = async (id: BookType['id']) => {
        const res = await BookService.take(id);
        if (res.ok) {
            refetch();
        }
    }

    const handleDelete = async (id: BookType['id']) => {
        const res = await BookService.delete(id)
        if (res.ok) {
            refetch();
        }
    }

    if (loading) {
        return <div>Loading...</div>
    }

    if (error) {
        return <div>Error</div>
    }

    return (
        <ul className='flex flex-col items-center gap-y-2'>
            <Link to="/books/add" className='underline font-semibold'>Add</Link>
            {data?.map(book => <Book key={book.id} {...book} onEdit={handleEdit} onTake={handleTake} onDelete={handleDelete} />)}
        </ul>
    )

}

export { Books };
export default Books;