package by.SanchukS.quizer.exceptions;

public class SeveralAnswersException extends RuntimeException {
    public SeveralAnswersException() {
        super("Several answers to one task");
    }
}
