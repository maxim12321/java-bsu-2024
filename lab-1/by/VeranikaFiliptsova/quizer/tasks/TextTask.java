package by.VeranikaFiliptsova.quizer.tasks;

import by.VeranikaFiliptsova.quizer.Result;
import by.VeranikaFiliptsova.quizer.Task;
import by.VeranikaFiliptsova.quizer.generators.PoolTaskGenerator;
/**
 * Задание с заранее заготовленным текстом.
 * Можно использовать {@link PoolTaskGenerator}, чтобы задавать задания такого типа.
 */
class TextTask implements Task {
    /**
     * @param text   текст задания
     * @param answer ответ на задание
     */
    String textCondition;
    String rightAnswer;

    TextTask(
            String text,
            String answer
    ) {
       textCondition = text;
       rightAnswer = answer;

    }

    @Override
    public String getText() {
        return textCondition;
    }

    @Override
    public Result validate(String answer) {
        if (rightAnswer.equals(answer)) {
            return Result.OK;
        }
        return Result.WRONG;
    }
    }
