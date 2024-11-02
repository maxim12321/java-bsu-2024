package by.SanchukS.quizer.exceptions;

public class NullArgumentException extends RuntimeException {
    public NullArgumentException(String argName) {
        super("Null argument: " + argName);
    }
}
