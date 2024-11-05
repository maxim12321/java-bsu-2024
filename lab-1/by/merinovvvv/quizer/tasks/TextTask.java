package by.merinovvvv.quizer.tasks;

import by.merinovvvv.quizer.Result;
import by.merinovvvv.quizer.Task;
import by.merinovvvv.quizer.generators.PoolTaskGenerator;

import java.util.Objects;

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

    @Override
    public String getText() {
        return text;
    }

    @Override
    public Result validate(String answer) {
        if (answer == null || answer.trim().isEmpty()) {
            return Result.INCORRECT_INPUT;
        }

        return Objects.equals(this.answer, answer) ? Result.OK : Result.WRONG;

    }
}