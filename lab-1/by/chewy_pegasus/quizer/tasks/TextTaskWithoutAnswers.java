package by.chewy_pegasus.quizer.tasks;

import by.chewy_pegasus.quizer.generators.PoolTaskGenerator;
import by.chewy_pegasus.quizer.Result;
import by.chewy_pegasus.quizer.tasks.text.TextTask;

/**
 * Задание с заранее заготовленным текстом.
 * Можно использовать {@link PoolTaskGenerator}, чтобы задавать задания такого типа.
 */
public class TextTaskWithoutAnswers extends TextTask {
    /**
     * @param text   текст задания
     * @param answer ответ на задание
     */
    private String text;
    private String answer;

    public TextTaskWithoutAnswers(
            String text,
            String answer
    ) {
        super(text, answer);
    }

    @Override
    public Result validate(String answer) {
        return this.correctAnswer.equals(answer) ? Result.OK : Result.WRONG;
    }
}
