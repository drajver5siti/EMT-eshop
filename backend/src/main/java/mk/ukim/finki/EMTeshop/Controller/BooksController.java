package mk.ukim.finki.EMTeshop.Controller;

import mk.ukim.finki.EMTeshop.DTO.BookDTO;
import mk.ukim.finki.EMTeshop.Enum.Category;
import mk.ukim.finki.EMTeshop.Model.Book;
import mk.ukim.finki.EMTeshop.Service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
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

    @GetMapping("/{id}")
    public ResponseEntity<Book> get(
            @PathVariable Long id
    ) {
        return this.books.findById(id)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PostMapping
    public ResponseEntity<Book> create(
            @RequestBody BookDTO bookDTO
            ) {

        return this.books.save(bookDTO)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> update(
            @PathVariable Long id,
            @RequestBody BookDTO bookDTO
    ) {
        return this.books.edit(id, bookDTO)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping("/{id}/take")
    public ResponseEntity take(
            @PathVariable Long id
    ) {
        this.books.take(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(
            @PathVariable Long id
    ) {
        this.books.deleteById(id);

        if(this.books.findById(id).isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok().build();
    }

}
