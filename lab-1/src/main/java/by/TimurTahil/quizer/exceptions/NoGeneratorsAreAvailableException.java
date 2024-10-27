package by.TimurTahil.quizer.exceptions;

public class NoGeneratorsAreAvailableException extends IllegalArgumentException {
    public NoGeneratorsAreAvailableException() {
        super("No generators are available");
    }
}
