package by.v10k13.quizer.tests.tester;

public class TestBehaviorFailureException extends TestFailureException {
    public TestBehaviorFailureException() {
        super("Unrecognized behavior detected.");
    }
    public TestBehaviorFailureException(RuntimeException ex) { super("Unrecognized behavior detected.", ex);}
}
