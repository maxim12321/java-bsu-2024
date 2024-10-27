package by.Simanenka_Alex.quizer.tasks;

import by.Simanenka_Alex.quizer.*;

public class TextTask implements Task {

    public TextTask(
            String text,
            String answer
    ) {
        this.text = text;
        this.answer = answer;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public Result validate(String answer) {
        if (this.answer.equals(answer)) {
            return Result.OK;
        }
        else {
            return Result.WRONG;
        }
    }

    private String text;
    private String answer;
}
