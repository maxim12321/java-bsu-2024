package by.SanchukS.quizer.exceptions;

public class QuizNotFinishedException extends RuntimeException {
    public QuizNotFinishedException() {
        super("Quiz not finished.");
    }
}
