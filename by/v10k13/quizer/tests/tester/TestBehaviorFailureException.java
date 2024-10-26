package by.v10k13.quizer.tests.tester;

public class TestBehaviorFailureException extends RuntimeException {
    public TestBehaviorFailureException() {
        super("Unrecognized behavior detected.");
    }
}
