package KlimovMikh.quizer.tasks;

import KlimovMikh.quizer.Result;
import KlimovMikh.quizer.Task;

/**
 * Задание с заранее заготовленным текстом.
 * Можно использовать PoolTaskGenerator, чтобы задавать задания такого типа.
 */
public class TextTask implements Task {
    private final String taskText;
    private final String taskAnswer;
    /**
     * @param text   текст задания
     * @param answer ответ на задание
     */
    public TextTask(
            String text,
            String answer
    ) {
        taskText = text;
        taskAnswer = answer;
    }

    @Override
    public String getText() {
        return taskText;
    }

    @Override
    public Result validate(String answer) {
        try {
            Integer.parseInt(answer);
        } catch (NumberFormatException e) {
            return Result.INCORRECT_INPUT;
        }
        if(answer.equals(taskAnswer)) {
            return Result.OK;
        } else {
            return Result.WRONG;
        }
    }
}
