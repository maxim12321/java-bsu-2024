package by.MatsveiZorka.quizer.tasks;

import by.MatsveiZorka.quizer.Result;

/**
 * Задание с заранее заготовленным текстом.
 * Можно использовать {@link PoolTaskGenerator}, чтобы задавать задания такого типа.
 */
class TextTask extends AbstractTask {
    private String Answer_;

    /**
     * @param text   текст задания
     * @param answer ответ на задание
     */

    TextTask(
            String text,
            String answer
    ) {
        super(text);
        Answer_ = answer;
    }

    @Override
    public Result validate(String answer) {
        return Answer_.equals(answer) ? Result.OK : Result.WRONG;
    }
}