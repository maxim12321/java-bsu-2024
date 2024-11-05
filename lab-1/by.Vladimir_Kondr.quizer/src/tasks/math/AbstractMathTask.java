package tasks.math;

import core.Result;

public abstract class AbstractMathTask implements MathTask {
    protected final Operation op;
    protected final int left;
    protected final int right;


    protected AbstractMathTask() {
        this.op = null;
        this.left = 0;
        this.right = 0;
    }

    protected AbstractMathTask(Operation op, int left, int right) throws IllegalArgumentException {
        this.op = op;
        this.left = left;
        this.right = right;
        boolean e = isValid();
        if (!e) {
            throw new IllegalArgumentException("Task is invalid");
        }

    }

    protected abstract int computeAnswer();
}