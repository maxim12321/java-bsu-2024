package by.AlbertRadoshko.quizer.tasks;


import by.AlbertRadoshko.quizer.Result;
import by.AlbertRadoshko.quizer.Task;

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
        this.ans = answer;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public Result validate(String answer) {
        if (answer.equals(ans)) {
            return Result.OK;
        }
        return Result.WRONG;
    }

    String text = "";
    String ans = "";
}
