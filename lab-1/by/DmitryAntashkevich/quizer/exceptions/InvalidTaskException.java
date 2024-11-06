package by.DmitryAntashkevich.quizer.exceptions;

public class InvalidTaskException extends IllegalArgumentException {
    public InvalidTaskException(String message) {
        super(message);
    }
}
