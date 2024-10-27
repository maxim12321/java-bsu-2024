package lab1.by.TyapkovArtem.quizer.exceptions;

public class QuizIsFinishedException extends RuntimeException{
    public QuizIsFinishedException(String string) {
        super(string);
    }
}
