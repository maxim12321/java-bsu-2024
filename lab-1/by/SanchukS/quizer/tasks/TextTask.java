package by.SanchukS.quizer.tasks;

import by.SanchukS.quizer.Result;
import by.SanchukS.quizer.Task;
import by.SanchukS.quizer.exceptions.NullArgumentException;
import by.SanchukS.quizer.generators.PoolTaskGenerator;

/**
 * Задание с заранее заготовленным текстом.
 * Можно использовать {@link PoolTaskGenerator}, чтобы задавать задания такого типа.
 */
class TextTask implements Task {
    private final String text;
    private final String answer;

    /**
     * @param text   текст задания
     * @param answer ответ на задание
     */
    TextTask(
            String text,
            String answer
    ) {
        if (text == null) throw new NullArgumentException("text");
        if (answer == null) throw new NullArgumentException("answer");
        this.text = text;
        this.answer = answer;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public Result validate(String answer) {
        return this.answer.equals(answer) ? Result.OK : Result.WRONG;
    }
}