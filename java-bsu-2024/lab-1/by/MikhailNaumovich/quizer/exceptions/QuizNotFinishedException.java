package by.MikhailNaumovich.quizer.exceptions;


public class QuizNotFinishedException extends RuntimeException {
    public QuizNotFinishedException(String message) {
        super(message);
    }
}