package lab1.by.TyapkovArtem.quizer.tester;

/**
 * @author <a href="https://github.com/10-13">10-13</a>
 */
public class TestFailureException extends RuntimeException {
    public TestFailureException(String message, Throwable cause) {
        super(message, cause);
    }
    public TestFailureException() {
        super("Test failed with unspecified reason.");
    }
    public TestFailureException(String message) {
        super(message);
    }
}
