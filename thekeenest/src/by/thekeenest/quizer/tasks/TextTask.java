package by.thekeenest.quizer.tasks;
import by.thekeenest.quizer.Result;

public class TextTask implements Task {
    private final String text;
    private String correctAnswer;

    public TextTask(
            String text,
            String answer
    ) {
            this.text = text;
            this.correctAnswer = answer;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public Result validate(String answer) {
        if (answer == correctAnswer) {
            return Result.OK;
        } else {
            return Result.WRONG;
        }
    }
}
