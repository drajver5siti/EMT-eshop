package mk.ukim.finki.EMTeshop.Controller;

import mk.ukim.finki.EMTeshop.Enum.Category;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/api/categories")
public class CategoriesController {
    @GetMapping
    List<Category> index()
    {
        return Arrays.asList(Category.values());
    }

}
