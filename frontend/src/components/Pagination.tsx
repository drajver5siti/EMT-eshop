import { Pageable } from "../types";

type Props = {
    pageable: Pageable,
    totalPages: number,
    onFetchPage: (page: number, size: number) => void
}

const Pagination = ({ pageable, totalPages, onFetchPage }: Props) => {

    const currentPage = pageable.pageNumber;
    const isFirstPage = currentPage === 0;
    const isLastPage = currentPage + 1 === totalPages;

    return (
        <div className="flex gap-x-[1px]">
            <button
                className='outline outline-1 h-7 aspect-square disabled:hover:cursor-not-allowed'
                disabled={isFirstPage}
                onClick={() => onFetchPage(currentPage - 1, pageable.pageSize)}
            >
                &#8592;
            </button>
            {Array.from({ length: totalPages }).map((_, i) => (
                <button
                    key={i}
                    className={`outline outline-black outline-1 h-7 aspect-square disabled:hover:cursor-not-allowed ${currentPage === i ? 'bg-slate-400 text-white' : ''}`}
                    disabled={currentPage === i}
                    onClick={() => onFetchPage(i, pageable.pageSize)}
                >
                    {i + 1}
                </button>
            ))}
            <button
                className="outline outline-1 h-7 aspect-square disabled:hover:cursor-not-allowed"
                disabled={isLastPage}
                onClick={() => onFetchPage(currentPage + 1, pageable.pageSize)}
            >
                &#8594;
            </button>
        </div>
    );
}

export { Pagination };
export default Pagination;