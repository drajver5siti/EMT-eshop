package mk.ukim.finki.EMTeshop.Service.impl;

import mk.ukim.finki.EMTeshop.DTO.CountryDTO;
import mk.ukim.finki.EMTeshop.Model.Country;
import mk.ukim.finki.EMTeshop.Repository.CountryRepository;
import mk.ukim.finki.EMTeshop.Service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countries;

    public CountryServiceImpl(CountryRepository countries) {
        this.countries = countries;
    }

    @Override
    public List<Country> findAll() {
        return this.countries.findAll();
    }

    @Override
    public Optional<Country> save(CountryDTO countryDTO) {
        Country country = new Country(countryDTO.getName(), countryDTO.getContinent());

        this.countries.save(country);
        return Optional.of(country);
    }
}
