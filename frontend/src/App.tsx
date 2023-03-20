import { Routes, Route, Navigate } from 'react-router-dom';
import AddBook from './components/AddBook';
import Books from './components/Books';
import Categories from './components/Categories';

function App() {
	return (
		<main>
			<Routes>
				<Route path='/' element={<Navigate to="/books" />} />
				<Route path='/books' element={<Books />} />
				<Route path='/books/add' element={<AddBook />} />
				<Route path='/categories' element={<Categories />} />
			</Routes>
		</main>
	)
}

export default App
