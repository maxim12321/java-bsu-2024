package by.Alesia.quizer.tasks;

import by.Alesia.quizer.Result;
import by.Alesia.quizer.Task;

/**
 * Задание с заранее заготовленным текстом.
 * Можно использовать {@link PoolTaskGenerator}, чтобы задавать задания такого типа.
 */
public class TextTask implements Task {
    /**
     * @param text   текст задания
     * @param answer ответ на задание
     */
    public TextTask(
            String text,
            String answer
    ) {
        text_ = text;
        answer_ = answer;
    }

    @Override
    public String getText() {
        return text_;
    }

    @Override
    public Result validate(String answer) {
        if (answer.equals(answer_)) {
            return Result.OK;
        }
        return Result.WRONG;
    }

    private final String text_;
    private final String answer_;
}