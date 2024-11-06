package by.AlexAgeev.quizer.exceptions;

public class NoGeneratorsAvailableException extends IllegalArgumentException {
    public NoGeneratorsAvailableException() {
        super("No generators are available");
    }
}
