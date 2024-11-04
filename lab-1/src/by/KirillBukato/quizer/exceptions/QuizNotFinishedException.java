package by.KirillBukato.quizer.exceptions;

public class QuizNotFinishedException extends RuntimeException {
    public QuizNotFinishedException() {
        super("Quiz is not finished, you can't get the result yet.");
    }
}
