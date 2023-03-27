import { BookType } from "../types/books";

type Props = BookType & {
    onEdit: (id: BookType['id']) => void,
    onTake: (id: BookType['id']) => void,
    onDelete: (id: BookType['id']) => void,
}

const Book = ({ id, name, author, category, availableCopies, onEdit, onTake, onDelete }: Props) => {

    return (
        <li className="border-emerald-800 px-6 py-2 border w-2/6 rounded-sm flex">
            <div className="flex-grow">
                <div className="flex flex-row gap-x-2">
                    <h3 className="font-semibold mb-8">{name}</h3>
                    <span>-</span>
                    <div>{category}</div>
                </div>
                <p className="text-sm">Available copies: {availableCopies}</p>
                <p className="text-sm">{author.name} {author.surname}</p>
            </div>
            <div className="flex flex-col text-sm gap-y-2 font-medium">
                <button className="px-2 py-1 rounded-sm border border-emerald-600 hover:bg-emerald-600 hover:text-white" onClick={() => onEdit(id)}>Edit</button>
                <button className="px-2 py-1 rounded-sm border border-emerald-600 hover:bg-emerald-600 hover:text-white disabled:opacity-25 disabled:cursor-not-allowed" onClick={() => onTake(id)} disabled={availableCopies === 0}>Take</button>
                <button className="px-2 py-1 rounded-sm border border-red-600 hover:bg-red-600 hover:text-white" onClick={() => onDelete(id)}>Delete</button>
            </div>
        </li>
    )
}

export { Book };
export default Book;