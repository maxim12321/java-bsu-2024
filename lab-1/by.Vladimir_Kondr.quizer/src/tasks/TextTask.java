package tasks;

import core.Result;
import core.Task;

import java.util.Objects;

public class TextTask implements Task {
    private final String text;
    private final String answer;
    /**
     * @param text   текст задания
     * @param answer ответ на задание
     */
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
        return (Objects.equals(this.answer, answer)) ? Result.OK : Result.WRONG;
    }
}