package by.maxsavitsky.quizer.tasks;

import by.maxsavitsky.quizer.Result;

public class TextTask implements Task {

    private final String text;
    private final String answer;

    public TextTask(String text, String answer) {
        this.text = text;
        this.answer = answer;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public Result validate(String answer) {
        return this.answer.equals(answer) ? Result.OK : Result.WRONG;
    }
}
