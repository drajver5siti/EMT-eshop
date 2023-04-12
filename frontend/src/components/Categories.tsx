import useFetch from "../hooks/useFetch";
import { CategoryType } from "../types";

const Categories: React.FC = () => {

    const { loading, error, data } = useFetch<CategoryType[]>('/api/categories');

    if (loading) {
        return <div>Loading...</div>
    }

    if (error) {
        return <div>Error</div>
    }

    return (
        <div className="flex flex-col items-center">
            {data?.map(category => <div className="flex justify-center border 1px solid black w-1/12" key={category}>{category}</div>)}
        </div>
    )
}

export { Categories };
export default Categories;