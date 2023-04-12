package mk.ukim.finki.EMTeshop.Service;

import mk.ukim.finki.EMTeshop.DTO.BookDTO;
import mk.ukim.finki.EMTeshop.Model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BookService {
    Page<Book> findAll(Pageable pageable);
    Optional<Book> findById(Long id);

    Optional<Book> save(BookDTO bookDTO);
    Optional<Book> edit(Long id, BookDTO bookDTO);

    void take(Long id);
    void deleteById(Long id);
}
