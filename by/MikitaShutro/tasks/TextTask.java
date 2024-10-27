package by.MikitaShutro.tasks;

import by.MikitaShutro.Result;
import by.MikitaShutro.generators.PoolTaskGenerator;

/**
 * Задание с заранее заготовленным текстом.
 * Можно использовать {@link PoolTaskGenerator}, чтобы задавать задания такого типа.
 */
class TextTask extends AbstractTask {
    /**
     * @param text   текст задания
     * @param answer ответ на задание
     */
    private String answer_;
    TextTask(
            String text,
            String answer
    ) {
        super(text);
        answer_ = answer;
    }

    @Override
    public Result validate(String answer) {
        return answer_.equals(answer) ? Result.OK : Result.WRONG;
    }
}