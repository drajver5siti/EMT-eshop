package mk.ukim.finki.EMTeshop.DTO;

import lombok.Data;
import mk.ukim.finki.EMTeshop.Enum.Category;

@Data
public class BookDTO {

    private String name;

    private Category category;

    private Long author;

    private Integer availableCopies;

    public BookDTO(String name, Category category, Long author, Integer availableCopies) {
        this.name = name;
        this.category = category;
        this.author = author;
        this.availableCopies = availableCopies;
    }
}
