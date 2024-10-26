package by.kirsiv40.quizer.Tasks;

import by.kirsiv40.quizer.Main.Result;

/**
 * Задание с заранее заготовленным текстом. 
 * Можно использовать {@link PoolTaskGenerator}, чтобы задавать задания такого типа.
 */
public class TextTask implements Task {
    private final String text;
    private final String ans;

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
        answer = answer.trim();
        return (answer.equals(this.ans)) ? Result.OK : Result.WRONG;
    }
}