package mk.ukim.finki.EMTeshop.Repository;

import mk.ukim.finki.EMTeshop.Model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
}
