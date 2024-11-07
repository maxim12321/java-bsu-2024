package by.volkovets.quizer.exceptions;

public class QuizNotFinishedException extends RuntimeException{
    public QuizNotFinishedException() {
        super("Test isn't finished. You can't see your mark now");
    }
}
