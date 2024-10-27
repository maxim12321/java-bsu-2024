package by.pkasila.quizer.tasks;

import by.pkasila.quizer.Result;
import by.pkasila.quizer.Task;
import by.pkasila.quizer.generators.PoolTaskGenerator;

/**
 * Задание с заранее заготовленным текстом.
 * Можно использовать {@link PoolTaskGenerator}, чтобы задавать задания такого типа.
 */
public class TextTask implements Task {
    private final String text;
    private final String answer;

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

    /**
     * @return the task in the text format
     */
    @Override
    public String getText() {
        return text;
    }

    /**
     * Check the answer to the task and returns result
     *
     * @param answer ответ на задание
     * @return результат ответа
     * @see Result
     */
    @Override
    public Result validate(String answer) {
        return this.answer.equals(answer) ? Result.OK : Result.WRONG;
    }
}
