package mk.ukim.finki.EMTeshop.Service;

import mk.ukim.finki.EMTeshop.DTO.BookDTO;
import mk.ukim.finki.EMTeshop.Exception.AuthorNotFoundException;
import mk.ukim.finki.EMTeshop.Model.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> findAll();
    Optional<Book> findById(Long id);

    Optional<Book> save(BookDTO bookDTO);
    Optional<Book> edit(Long id, BookDTO bookDTO);

    void take(Long id);
    void deleteById(Long id);
}
