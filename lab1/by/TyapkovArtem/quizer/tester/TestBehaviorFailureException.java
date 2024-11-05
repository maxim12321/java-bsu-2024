package lab1.by.TyapkovArtem.quizer.tester;

/**
 * @author <a href="https://github.com/10-13">10-13</a>
 */
public class TestBehaviorFailureException extends TestFailureException {
    public TestBehaviorFailureException() {
        super("Unrecognized behavior detected.");
    }
    public TestBehaviorFailureException(RuntimeException ex) { super("Unrecognized behavior detected.", ex);}
}
