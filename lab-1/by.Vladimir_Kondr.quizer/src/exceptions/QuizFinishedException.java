package exceptions;

public class QuizFinishedException extends RuntimeException {
    public QuizFinishedException(String message) {
        super(message);
    }
}
