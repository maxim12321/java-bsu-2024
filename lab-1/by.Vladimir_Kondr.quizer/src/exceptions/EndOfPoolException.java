package exceptions;

public class EndOfPoolException extends RuntimeException {
    public EndOfPoolException(String message) {
        super(message);
    }
}
