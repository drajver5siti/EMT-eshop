import { Link } from "react-router-dom";

const NavBar: React.FC = () => {
    return (
        <nav>
            <ul className="flex flex-row gap-x-10 px-10 py-2 text-xl text-gray-900">
                <li>
                    <Link to="/books">Books</Link>
                </li>
                <li>
                    <Link to="/categories">Categories</Link>
                </li>
                <li>
                    <Link to="/authors/add">Add Author</Link>
                </li>
                <li>
                    <Link to="/countries/add">Add Country</Link>
                </li>
            </ul>
        </nav>
    )
}

export { NavBar };
export default NavBar;