package by.VeranikaFiliptsova.quizer.exceptions;

public class GeneratorNotValidException extends RuntimeException {
    public GeneratorNotValidException(String message) {

        super("Unable to generate valid task: " + message);
    }
}
