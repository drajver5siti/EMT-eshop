package mk.ukim.finki.EMTeshop.Repository;

import mk.ukim.finki.EMTeshop.Model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
}
