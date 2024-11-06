package by.DmitryAntashkevich.quizer.tasks.math;

import by.DmitryAntashkevich.quizer.Result;

public abstract class AbstractMathTask implements MathTask {
    AbstractMathTask(int lhs, MathTask.Operation operation, int rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
        this.operation = operation;
    }

    @Override
    public Result validate(String answer) {
        try {
            return Integer.parseInt(answer) == getAnswer() ? Result.OK : Result.WRONG;
        } catch(NumberFormatException e) {
            return Result.INCORRECT_INPUT;
        }
    }

    protected String toString(int operand) {
        return operand >= 0 ? String.valueOf(operand) : "(" + String.valueOf(operand) + ")";
    }

    protected final int lhs;
    protected final int rhs;
    protected final MathTask.Operation operation;
}
