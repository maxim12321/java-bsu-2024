package by.TimurTahil.quizer.tasks;

import by.TimurTahil.quizer.Result;
import by.TimurTahil.quizer.Task;
import by.TimurTahil.quizer.generators.PoolTaskGenerator;

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
    public TextTask(String text, String answer) {
        this.text = text;
        this.answer = answer;
    }

    public String getText() {
        return this.text;
    }

    public Result validate(String answer) {
        if (this.answer.equals(answer)) {
            return Result.OK;
        }
        return Result.WRONG;
    }
}
