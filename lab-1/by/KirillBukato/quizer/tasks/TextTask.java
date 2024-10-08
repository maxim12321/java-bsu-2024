package by.KirillBukato.quizer.tasks;

import by.KirillBukato.quizer.Result;
import by.KirillBukato.quizer.Task;
import by.KirillBukato.quizer.generators.PoolTaskGenerator;

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
        this.text = text;
        this.answer = answer;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public Result validate(String answer) {
        if(answer.equals(this.answer)) {
            return Result.OK;
        } else {
            return Result.WRONG;
        }
    }

    protected final String text;
    protected final String answer;
}
