package mk.ukim.finki.EMTeshop.Service;

import mk.ukim.finki.EMTeshop.DTO.CountryDTO;
import mk.ukim.finki.EMTeshop.Model.Country;

import java.util.Optional;

public interface CountryService {
    Optional<Country> save(CountryDTO countryDTO);
}
