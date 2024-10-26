package by.MikhailNaumovich.quizer.tasks;

import by.MikhailNaumovich.quizer.Task;
import by.MikhailNaumovich.quizer.Result;
import by.MikhailNaumovich.quizer.generators.PoolTaskGenerator;

/**
 * Задание с заранее заготовленным текстом. 
 * Можно использовать {@link PoolTaskGenerator}, чтобы задавать задания такого типа.
 */

 
public class TextTask implements Task {
    /**
     * @param text   текст задания
     * @param answer ответ на задание
     */
    private final String text;
    private final String answer;

    public TextTask(
        String text,
        String answer
    ) {
        if (text == null) {
            throw new IllegalArgumentException("Text is null");
        }
        if (answer == null) {
            throw new IllegalArgumentException("Answer is null");
        }
        this.text = text;
        this.answer = answer;
    }
    @Override
    public String getText() {
        if (text == null) {
            throw new IllegalArgumentException("Text is null");
        }
        return text;
    }

    @Override
    public Result validate(String answer) {
        if (answer == null) {
            throw new IllegalArgumentException("Answer is null");
        }
        return this.answer.equals(answer) ? Result.OK : Result.WRONG;
    }

}