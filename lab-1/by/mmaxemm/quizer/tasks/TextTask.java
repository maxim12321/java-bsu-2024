package by.mmaxemm.quizer.tasks;

import by.mmaxemm.quizer.Result;
import by.mmaxemm.quizer.Task;
import by.mmaxemm.quizer.generators.PoolTaskGenerator;

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
        return answer.trim().toLowerCase() == this.answer.trim().toLowerCase()
                ? Result.OK : Result.WRONG;
    }
}