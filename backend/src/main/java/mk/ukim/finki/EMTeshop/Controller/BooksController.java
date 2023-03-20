package mk.ukim.finki.EMTeshop.Controller;

import mk.ukim.finki.EMTeshop.DTO.BookDTO;
import mk.ukim.finki.EMTeshop.Enum.Category;
import mk.ukim.finki.EMTeshop.Model.Book;
import mk.ukim.finki.EMTeshop.Service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BooksController {

    private final BookService books;

    public BooksController(BookService books) {
        this.books = books;
    }

    @GetMapping
    public List<Book> index() {
        return this.books.findAll();
    }

    @PostMapping
    public ResponseEntity<Book> create(
            @RequestBody BookDTO bookDTO
            ) {

        return this.books.save(bookDTO)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
}
