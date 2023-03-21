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
        <>
            {data?.map(category => <div key={category}>{category}</div>)}
        </>
    )
}

export { Categories };
export default Categories;