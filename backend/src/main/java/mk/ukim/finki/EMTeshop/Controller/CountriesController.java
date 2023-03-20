package mk.ukim.finki.EMTeshop.Controller;

import mk.ukim.finki.EMTeshop.DTO.CountryDTO;
import mk.ukim.finki.EMTeshop.Model.Country;
import mk.ukim.finki.EMTeshop.Service.CountryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/countries")
public class CountriesController {

    private final CountryService countries;

    public CountriesController(CountryService countries) {
        this.countries = countries;
    }

    @PostMapping
    ResponseEntity<Country> create(
            @RequestBody CountryDTO countryDTO
    ) {
        return this.countries.save(countryDTO)
                .map(country -> ResponseEntity.ok().body(country))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
}
