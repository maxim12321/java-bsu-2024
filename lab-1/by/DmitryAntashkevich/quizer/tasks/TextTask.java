package by.DmitryAntashkevich.quizer.tasks;

import by.DmitryAntashkevich.quizer.Result;
import by.DmitryAntashkevich.quizer.Task;
import by.DmitryAntashkevich.quizer.generators.PoolTaskGenerator;

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
        return this.text;
    }

    @Override
    public Result validate(String answer) {
        return this.answer.equals(answer) ? Result.OK : Result.WRONG;
    }

    private final String text;
    private final String answer;
}