package tasks;

import tasks.math.AbstractMathTask;

public class EquationTask extends AbstractMathTask {
    private final boolean isXFirst;

    public EquationTask(int a, Operation op, int result, boolean isXFirst) {
        super(op, a, result);
        this.isXFirst = isXFirst;
    }

    @Override
    protected int computeAnswer() {
        if (this.isXFirst) {
            return op.getOpposite().perform(right, left);
        }
        return switch(this.op) {
            case ADDITION, SUBTRACTION, MULTIPLICATION -> op.getOpposite().perform(right, left);
            case DIVISION -> op.perform(left, right);
        };
    }


    @Override
    public boolean isValid() {
        return !(!isXFirst && op == Operation.DIVISION && (right == 0 || left == 0)) &&
                !(op == Operation.DIVISION && left == 0 && isXFirst) &&
                !(op == Operation.MULTIPLICATION && left == 0);
    }

    @Override
    public String getText() {
        if (this.isXFirst) {
            return "x " + op.getSymbol() + " " + left + " = " + right;
        }
        return left + " " + op.getSymbol() + " x" + " = " + right;
    }
}
