package mk.ukim.finki.EMTeshop.Service.impl;

import mk.ukim.finki.EMTeshop.DTO.AuthorDTO;
import mk.ukim.finki.EMTeshop.Exception.CountryNotFoundException;
import mk.ukim.finki.EMTeshop.Model.Author;
import mk.ukim.finki.EMTeshop.Model.Country;
import mk.ukim.finki.EMTeshop.Repository.AuthorRepository;
import mk.ukim.finki.EMTeshop.Repository.CountryRepository;
import mk.ukim.finki.EMTeshop.Service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authors;
    private final CountryRepository countries;

    public AuthorServiceImpl(AuthorRepository authors, CountryRepository countries) {
        this.authors = authors;
        this.countries = countries;
    }

    @Override
    public List<Author> findAll() {
        return this.authors.findAll();
    }

    @Override
    public Optional<Author> findById(Long id) {
        return this.authors.findById(id);
    }

    @Override
    public Optional<Author> save(AuthorDTO authorDTO) {
        Country country = this.countries.findById(authorDTO.getCountry())
                .orElseThrow(() -> new CountryNotFoundException(authorDTO.getCountry()));

        Author author = new Author(authorDTO.getName(), authorDTO.getSurname(), country);

        this.authors.save(author);
        return Optional.of(author);
    }
}
