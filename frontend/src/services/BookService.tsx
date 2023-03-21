import { BookType } from "../types";

const API = 'http://localhost:9090/api/books';

type BookServiceType = {
    add: (data: Omit<BookType, 'id'>) => Promise<Response>,
    delete: (id: BookType['id']) => Promise<Response>,
    take: (id: BookType['id']) => Promise<Response>
}

const BookService: BookServiceType = {
    add: async (data) => await fetch((API), { method: 'POST', body: JSON.stringify(data) }),
    delete: async (id) => await fetch((API + '/' + id), { method: 'DELETE' }),
    take: async (id) => await fetch((API + '/' + id + '/take'), { method: 'GET' })
}

export { BookService };
export default BookService;