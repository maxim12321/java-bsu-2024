package by.KuryanovichNikita.quizer.tasks;

import by.KuryanovichNikita.quizer.Result;
import by.KuryanovichNikita.quizer.generators.PoolTaskGenerator;

/**
 * Задание с заранее заготовленным текстом.
 * Можно использовать {@link PoolTaskGenerator}, чтобы задавать задания такого типа.
 */
public class TextTask implements Task {

    private final String text;
    private final String corrAnswer;
    /**
     * @param text   текст задания
     * @param answer ответ на задание
     */
    public TextTask(
            String text,
            String answer
    ) {
        this.text = text;
        this.corrAnswer = answer;
    }

    @Override
    public String getText() {
        return this.text;
    }

    @Override
    public Result validate(String answer) {
        if(this.corrAnswer.equals(answer))
            return Result.OK;
        else
            return  Result.WRONG;
        }

    }