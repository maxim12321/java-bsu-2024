package tasks;

import tasks.math.AbstractMathTask;
import tasks.math.MathTask;

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
        String text;
        if (this.isXFirst) {
            text = "x " + op.getSymbol() + " " + left + " = " + right;
        } else {
            text = left + " " + op.getSymbol() + " x" + " = " + right;
        }
        text += String.format(" (если ответ нецелый, укажите его с точностью до %d-х знаков после запятой, с округлением вниз)", MathTask.Operation.getAccuracy());
        return text;
    }
}