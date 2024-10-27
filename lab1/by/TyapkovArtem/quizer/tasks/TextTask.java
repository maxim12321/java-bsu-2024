package lab1.by.TyapkovArtem.quizer.tasks;

import lab1.by.TyapkovArtem.quizer.Result;

/**
 * Задание с заранее заготовленным текстом.
 * Можно использовать {@link PoolTaskGenerator}, чтобы задавать задания такого типа.
 */
public class TextTask extends AbstractTask {

    private final String Answer_;
    public TextTask(String text, String answer) {
        super(text);
        Answer_ = answer;
    }



    public Result validate(String answer) {
        if (Answer_.equals(answer)) {
            return Result.OK;
        } else return Result.WRONG;
    }
}