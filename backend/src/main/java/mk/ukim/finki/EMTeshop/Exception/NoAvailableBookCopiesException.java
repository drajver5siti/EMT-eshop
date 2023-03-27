package mk.ukim.finki.EMTeshop.Exception;

public class NoAvailableBookCopiesException extends RuntimeException{
    public NoAvailableBookCopiesException(Long id){
        super(String.format("No available copies for book %d", id));
    }

}
