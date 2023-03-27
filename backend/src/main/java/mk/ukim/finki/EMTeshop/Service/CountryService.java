package mk.ukim.finki.EMTeshop.Service;

import mk.ukim.finki.EMTeshop.DTO.CountryDTO;
import mk.ukim.finki.EMTeshop.Model.Country;

import java.util.List;
import java.util.Optional;

public interface CountryService {
    List<Country> findAll();
    Optional<Country> save(CountryDTO countryDTO);
}
