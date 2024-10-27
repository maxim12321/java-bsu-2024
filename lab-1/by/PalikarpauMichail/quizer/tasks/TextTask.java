package by.PalikarpauMichail.quizer.tasks;

import by.PalikarpauMichail.quizer.Result;
import by.PalikarpauMichail.quizer.Task;
import by.PalikarpauMichail.quizer.generators.PoolTaskGenerator;

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

    /**
     @return текст задания
     */
    @Override
    public String getText() {
        return text;
    }

    /**
     * Проверяет ответ на задание и возвращает результат
     *
     * @param  answer ответ на задание
     * @return        результат ответа
     * @see           Result
     */
    @Override
    public Result validate(String answer) {
        if (answer.equals(this.answer)) {
            return Result.OK;
        } else {
            return Result.WRONG;
        }
    }

    String text;
    String answer;
}
