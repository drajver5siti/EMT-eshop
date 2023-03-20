import React from 'react';
import useFetch from '../hooks/useFetch';

const Books: React.FC = () => {

    const { loading, error, response } = useFetch(() => fetch('/api/books'));

    return (
        <div className='text-red-300'>Books</div>
    )
}

export { Books };
export default Books;