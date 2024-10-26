package by.v10k13.quizer.tests.tester;

public class TestAssertionFailureException extends TestFailureException {
    public TestAssertionFailureException() {
        super("Failed on assert.");
    }
}
