package by.TimurTahil.quizer.exceptions;

public class QuizNotFinishedException extends RuntimeException {
    public QuizNotFinishedException() {
        super("Quiz isn't finished!");
    }
}
