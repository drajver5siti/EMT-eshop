import React from 'react';
import { Link, useNavigate } from 'react-router-dom';
import useFetch from '../hooks/useFetch';
import BookService from '../services/BookService';
import { BookType } from '../types/books';
import Book from './Book';
import { Page } from '../types';
import Pagination from './Pagination';

const Books = () => {

    const navigate = useNavigate();
    const { loading, error, data, refetch } = useFetch<Page<BookType[]>>('/api/books', { page: 0, size: 5 });

    const handleEdit = (id: BookType['id']) => {
        navigate('/books/edit/' + id);
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
            {data?.content.map(book => <Book key={book.id} {...book} onEdit={handleEdit} onTake={handleTake} onDelete={handleDelete} />)}
            {
                data?.pageable &&
                <Pagination
                    pageable={data.pageable}
                    totalPages={data.totalPages}
                    onFetchPage={(page, size) => refetch({ page, size })}
                />
            }
        </ul>
    )

}

export { Books };
export default Books;