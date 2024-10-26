package by.v10k13.quizer.tests.tester;

public class TestFailureException extends RuntimeException {
    public TestFailureException() {
        super("Test failed with unspecified reason.");
    }
    public TestFailureException(String message) {
        super(message);
    }
}
