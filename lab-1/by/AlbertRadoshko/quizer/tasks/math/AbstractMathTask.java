package by.AlbertRadoshko.quizer.tasks.math;

import by.AlbertRadoshko.quizer.Result;

public abstract class AbstractMathTask implements MathTask {
    AbstractMathTask(int x, int y, MathTask.Operation op) {
        this.lhs = x;
        this.rhs = y;
        this.op = op;
    }

    protected String str(int x) {
        if (x >= 0) {
            return String.valueOf(x);
        }
        return "(" + String.valueOf(x) + ")";
    }

    @Override
    public Result validate(String answer) {
        try {
            if (Integer.parseInt(answer) == getAnswer()) {
                return Result.OK;
            }
            return Result.WRONG;
        } catch(NumberFormatException e) {
            return Result.INCORRECT_INPUT;
        }
    }

    public int getLhs() {
        return lhs;
    }

    public int getRhs() {
        return rhs;
    }

    public MathTask.Operation getOp() {
        return op;
    }

    protected int lhs;
    protected int rhs;
    protected MathTask.Operation op;
}
