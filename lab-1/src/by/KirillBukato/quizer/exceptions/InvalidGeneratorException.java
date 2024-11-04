package by.KirillBukato.quizer.exceptions;

public class InvalidGeneratorException extends RuntimeException {
    public InvalidGeneratorException(String message) {
        super("This generator is invalid. " + message);
    }
}
