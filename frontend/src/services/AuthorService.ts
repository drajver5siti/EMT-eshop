import { AddAuthorType } from "../types/authors";

const API = 'http://localhost:9090/api/authors';

const headers = {
    'Content-Type': 'application/json'
}

type AuthorServiceType = {
    add: (data: AddAuthorType) => Promise<Response>
}

const AuthorService: AuthorServiceType = {
    add: async (data) => await fetch(API, { method: 'POST', body: JSON.stringify(data), headers})
}

export { AuthorService };
export default AuthorService;