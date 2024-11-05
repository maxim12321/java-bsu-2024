package lab1.by.TyapkovArtem.quizer.tester;

/**
 * @author <a href="https://github.com/10-13">10-13</a>
 */
public class TestAssertionFailureException extends TestFailureException {
    public TestAssertionFailureException() {
        super("Failed on assert.");
    }
}
