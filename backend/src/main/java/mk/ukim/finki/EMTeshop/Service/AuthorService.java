package mk.ukim.finki.EMTeshop.Service;

import mk.ukim.finki.EMTeshop.DTO.AuthorDTO;
import mk.ukim.finki.EMTeshop.Model.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    List<Author> findAll();
    Optional<Author> findById(Long id);
    Optional<Author> save(AuthorDTO authorDTO);
}
