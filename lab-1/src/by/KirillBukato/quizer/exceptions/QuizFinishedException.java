package by.KirillBukato.quizer.exceptions;

public class QuizFinishedException extends RuntimeException {
    public QuizFinishedException(String message) {
        super("Quiz is finished. " + message);
    }
}
