package mk.ukim.finki.EMTeshop.Repository;

import mk.ukim.finki.EMTeshop.Model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
