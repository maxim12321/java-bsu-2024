package by.v10k13.quizer.exceptions;

public class TestNotFinishedYetException extends RuntimeException {
    public TestNotFinishedYetException() {
        super("Test not finished.");
    }
}
