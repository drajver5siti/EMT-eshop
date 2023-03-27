package mk.ukim.finki.EMTeshop.Model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "authors")
public class Author {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String surname;

    @OneToOne
    private Country country;

    public Author() {
    }

    public Author(String name, String surname, Country country) {
        this.name = name;
        this.surname = surname;
        this.country = country;
    }
}
