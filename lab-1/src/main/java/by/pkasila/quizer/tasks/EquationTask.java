package by.pkasila.quizer.tasks;

import by.pkasila.quizer.Result;
import by.pkasila.quizer.tasks.math.AbstractMathTask;

public class EquationTask<T extends Number> extends AbstractMathTask<T> {
    /**
     * @return the task in the text format
     */
    @Override
    public String getText() {
        return "";
    }

    /**
     * Check the answer to the task and returns result
     *
     * @param answer ответ на задание
     * @return результат ответа
     * @see Result
     */
    @Override
    public Result validate(String answer) {
        return null;
    }
}
