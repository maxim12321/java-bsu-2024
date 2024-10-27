package by.pkasila.quizer.tasks.math;

import by.pkasila.quizer.common.MathTask;
import by.pkasila.quizer.common.Result;
import by.pkasila.quizer.common.MathOperation;

public abstract class AbstractMathTask implements MathTask {

    protected final int left;

    protected final MathOperation operator;

    protected final int right;

    public AbstractMathTask(int left, MathOperation operator, int right) {
        this.left = left;
        this.operator = operator;
        this.right = right;
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
        double correctAnswer = computeAnswer();
        double userAnswer;
        try {
            userAnswer = Double.parseDouble(answer);
        } catch (NumberFormatException e) {
            return Result.INCORRECT_INPUT;
        }
        if (Math.abs(userAnswer - correctAnswer) < 0.001) {
            return Result.OK;
        } else {
            return Result.WRONG;
        }
    }
}
