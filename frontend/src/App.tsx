import { Routes, Route, Navigate } from 'react-router-dom';
import AddBook from './components/AddBook';
import Books from './components/Books';
import Categories from './components/Categories';
import AddCountry from './components/AddCountry';
import AddAuthor from './components/AddAuthor';
import EditBook from './components/EditBook';

function App() {
	return (
		<main>
			<Routes>
				<Route path='/' element={<Navigate to="/books" />} />
				<Route path='/books' element={<Books />} />
				<Route path='/books/edit/:id' element={<EditBook />} />
				<Route path='/books/add' element={<AddBook />} />
				<Route path='/categories' element={<Categories />} />
				<Route path='/authors/add' element={<AddAuthor />} />
				<Route path='/countries/add' element={<AddCountry />} />
			</Routes>
		</main>
	)
}

export default App
