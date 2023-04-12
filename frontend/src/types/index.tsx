export type CategoryType = "NOVEL" | "THRILER" | "HISTORY" | "FANTASY" | "BIOGRAPHY" | "CLASSICS" | "DRAMA";
export const categoryOptions: CategoryType[] = ["NOVEL", "THRILER", "HISTORY", "FANTASY", "BIOGRAPHY", "CLASSICS", "DRAMA"];

export type Pageable = {
    pageNumber: number,
    pageSize: number
}

export type Page<T> = {
    content: T,
    totalPages: number,
    pageable: Pageable
};
