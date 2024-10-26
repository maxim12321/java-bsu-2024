package by.MikhailNaumovich.quizer.exceptions;

public class QuizFinishedException extends RuntimeException {
    public QuizFinishedException(String message) {
        super(message);
    }
}