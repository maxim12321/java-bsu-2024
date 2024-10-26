package by.chewy_pegasus.quizer.exceptions;

public class QuizNotFinishedException extends Exception{
    private String message;
    public QuizNotFinishedException() {
        message = "";
    }
    public QuizNotFinishedException(String message) {
        this.message = "Quiz is not finished: " + message;
    }
}
