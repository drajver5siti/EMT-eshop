package mk.ukim.finki.EMTeshop.Controller;

import mk.ukim.finki.EMTeshop.DTO.AuthorDTO;
import mk.ukim.finki.EMTeshop.Model.Author;
import mk.ukim.finki.EMTeshop.Service.AuthorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorsController {

    private final AuthorService authors;

    public AuthorsController(AuthorService authors) {
        this.authors = authors;
    }

    @GetMapping
    public List<Author> index() {
        return this.authors.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> find(
            @PathVariable Long id
    ) {
        return this.authors.findById(id)
                .map(author -> ResponseEntity.ok().body(author))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Author> create(
            @RequestBody AuthorDTO authorDTO
    ) {
        return this.authors.save(authorDTO)
                .map(author -> ResponseEntity.ok().body(author))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
}
