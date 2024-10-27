package by.pkasila.quizer.tasks.math;

import by.pkasila.quizer.Result;

public abstract class AbstractMathTask<T extends Number> implements MathTask {
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
