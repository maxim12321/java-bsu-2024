package tasks.math;

import core.Result;

public abstract class AbstractMathTask implements MathTask {
    protected final Operation op;
    protected final int left;
    protected final int right;
    protected final int answer;

    protected AbstractMathTask() {
        this.op = null;
        this.left = 0;
        this.right = 0;
        this.answer = 0;
    }

    protected AbstractMathTask(Operation op, int left, int right) {
        this.op = op;
        this.left = left;
        this.right = right;
        boolean e = isValid();
        if (!e) {
            throw new IllegalArgumentException("Task is invalid");
        }
        this.answer = computeAnswer();
    }

    /**
     * Проверяет ответ на задание и возвращает результат
     *
     * @param  answer ответ на задание
     * @return        результат ответа
     * @see           Result
     */
    public Result validate(String answer) {
        try {
            double ans = Double.parseDouble(answer);
            return (this.answer == ((int) (ans * MathTask.Operation.pow10()))) ? Result.OK : Result.WRONG;

        } catch (NumberFormatException e) {
            return Result.INCORRECT_INPUT;
        }
    }

    protected abstract int computeAnswer();
}