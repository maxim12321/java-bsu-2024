package lab1.by.TyapkovArtem.quizer.exceptions;

public class QuizNotFinishedException extends RuntimeException{
    public QuizNotFinishedException(String string) {
        super(string);
    }
}
