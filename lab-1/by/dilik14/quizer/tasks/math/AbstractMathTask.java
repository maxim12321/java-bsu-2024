package by.dilik14.quizer.tasks.math;

import by.dilik14.quizer.Result;

public abstract class AbstractMathTask implements MathTask {
    AbstractMathTask(int lhs, Operation operation, int rhs) {
        this.lhs = lhs;
        this.operation = operation;
        this.rhs = rhs;
    }

    @Override
    public Result validate(String answer) {
        try {
            return Integer.parseInt(answer) == computeAnswer() ? Result.OK : Result.WRONG;
        } catch (NumberFormatException e) {
            return Result.INCORRECT_INPUT;
        }
    }

    protected String toString(int operand) {
        return operand >= 0 ? String.valueOf(operand) : "(" + String.valueOf(operand) + ")";
    }

    protected final int lhs;
    protected final Operation operation;
    protected final int rhs;
}
