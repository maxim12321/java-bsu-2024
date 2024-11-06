package by.DmitryAntashkevich.quizer.exceptions;

public class QuizNotFinishedException extends RuntimeException {
    public QuizNotFinishedException(String message) {
        super(message);
    }
}
