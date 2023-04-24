package mk.ukim.finki.EMTeshop.Exception;

public class InvalidUsernameOrPasswordException extends RuntimeException{
    public InvalidUsernameOrPasswordException() {
        super("Invalid Username or Password");
    }
}
