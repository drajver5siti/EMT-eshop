package mk.ukim.finki.EMTeshop.Controller;

import mk.ukim.finki.EMTeshop.DTO.CountryDTO;
import mk.ukim.finki.EMTeshop.Model.Country;
import mk.ukim.finki.EMTeshop.Service.CountryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/api/countries")
public class CountriesController {

    private final CountryService countries;

    public CountriesController(CountryService countries) {
        this.countries = countries;
    }

    @GetMapping
    List<Country> index()
    {
        return this.countries.findAll();
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
