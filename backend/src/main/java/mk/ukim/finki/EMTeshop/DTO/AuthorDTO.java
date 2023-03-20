package mk.ukim.finki.EMTeshop.DTO;

import lombok.Data;
import mk.ukim.finki.EMTeshop.Model.Country;

@Data
public class AuthorDTO {
    private String name;

    private String surname;

    private Long country;
}
