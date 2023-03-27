import { AddBookType, BookType } from "../types/books";

const API = 'http://localhost:9090/api/books';

const headers = {
    'Content-Type': 'application/json'
}

type EditBookType = AddBookType;

type BookServiceType = {
    add: (data: AddBookType) => Promise<Response>,
    edit: (id: BookType['id'], data:EditBookType) => Promise<Response>,
    delete: (id: BookType['id']) => Promise<Response>,
    take: (id: BookType['id']) => Promise<Response>
}

const BookService: BookServiceType = {
    add: async (data) => await fetch((API), { method: 'POST', body: JSON.stringify(data), headers }),
    edit: async (id, data) => await fetch((API + '/' + id), { method: 'PUT', body: JSON.stringify(data), headers}),
    delete: async (id) => await fetch((API + '/' + id), { method: 'DELETE', headers }),
    take: async (id) => await fetch((API + '/' + id + '/take'), { method: 'GET', headers })
}

export { BookService };
export default BookService;