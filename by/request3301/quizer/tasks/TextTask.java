package by.request3301.quizer.tasks;

import by.request3301.quizer.Result;
import by.request3301.quizer.Task;
import by.request3301.quizer.generators.PoolTaskGenerator;

/**
 * Задание с заранее заготовленным текстом.
 * Можно использовать {@link PoolTaskGenerator}, чтобы задавать задания такого типа.
 */
public class TextTask implements Task {
    String text;
    String answer;

    /**
     * @param text   текст задания
     * @param answer ответ на задание
     */
    public TextTask(
            String text,
            String answer
    ) {
        this.text = text;
        this.answer = answer;
    }

    public String getText() {
        return text;
    }

    public Result validate(String answer) {
        return this.answer.equals(answer) ? Result.OK : Result.WRONG;
    }
}