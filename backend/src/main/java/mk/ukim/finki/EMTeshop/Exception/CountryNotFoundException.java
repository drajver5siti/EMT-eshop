package mk.ukim.finki.EMTeshop.Exception;

public class CountryNotFoundException extends RuntimeException{
    public CountryNotFoundException(Long id) {
        super(String.format("Country with id %d not found", id));
    }
}
