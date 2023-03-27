package mk.ukim.finki.EMTeshop.Service.impl;

import mk.ukim.finki.EMTeshop.DTO.BookDTO;
import mk.ukim.finki.EMTeshop.Exception.AuthorNotFoundException;
import mk.ukim.finki.EMTeshop.Exception.BookNotFoundException;
import mk.ukim.finki.EMTeshop.Exception.NoAvailableBookCopiesException;
import mk.ukim.finki.EMTeshop.Model.Author;
import mk.ukim.finki.EMTeshop.Model.Book;
import mk.ukim.finki.EMTeshop.Repository.AuthorRepository;
import mk.ukim.finki.EMTeshop.Repository.BookRepository;
import mk.ukim.finki.EMTeshop.Service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository books;
    private final AuthorRepository authors;

    public BookServiceImpl(BookRepository books, AuthorRepository authors) {
        this.books = books;
        this.authors = authors;
    }

    @Override
    public List<Book> findAll() {
        return this.books.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return this.books.findById(id);
    }

    @Override
    public Optional<Book> save(BookDTO bookDTO) {
        Author author = this.authors.findById(bookDTO.getAuthor())
                .orElseThrow(() -> new AuthorNotFoundException(bookDTO.getAuthor()));

        Book book = new Book(bookDTO.getName(), bookDTO.getCategory(), author, bookDTO.getAvailableCopies());

        this.books.save(book);
        return Optional.of(book);
    }

    @Override
    public Optional<Book> edit(Long id, BookDTO bookDTO) {
        Book book = this.books.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        Author author = this.authors.findById(bookDTO.getAuthor()).orElseThrow(() -> new AuthorNotFoundException(bookDTO.getAuthor()));

        book.setAuthor(author);
        book.setName(bookDTO.getName());
        book.setAvailableCopies(bookDTO.getAvailableCopies());
        book.setCategory(bookDTO.getCategory());

        this.books.save(book);
        return Optional.of(book);
    }

    public void take(Long id) {
        Book book = this.books.findById(id).orElseThrow(() -> new BookNotFoundException(id));

        if(book.getAvailableCopies() == 0) {
            throw new NoAvailableBookCopiesException(id);
        }

        book.setAvailableCopies(book.getAvailableCopies() - 1);

        this.books.save(book);
    }


    @Override
    public void deleteById(Long id) {
        this.books.deleteById(id);
    }
}
