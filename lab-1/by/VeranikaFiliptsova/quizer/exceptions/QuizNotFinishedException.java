package by.VeranikaFiliptsova.quizer.exceptions;

public class QuizNotFinishedException extends RuntimeException {
    public QuizNotFinishedException() {
        super("Can't get mark before finishing quiz");
    }
}
