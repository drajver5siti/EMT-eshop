import { AuthorType } from "../types"

const API = 'http://localhost:9090/api/authors';

type AuthorServiceType = {
    add: (data: Omit<AuthorType, 'id'>) => Promise<Response>
}

const AuthorService: AuthorServiceType = {
    add: async (data) => await fetch(API, { method: 'POST', body: JSON.stringify(data) })
}

export { AuthorService };
export default AuthorService;