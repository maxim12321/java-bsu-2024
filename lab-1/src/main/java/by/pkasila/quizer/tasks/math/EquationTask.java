package by.pkasila.quizer.tasks.math;

import by.pkasila.quizer.common.MathOperation;

public class EquationTask extends AbstractMathTask {

    private final boolean isLeftX;

    public EquationTask(int left, MathOperation operator, int right, boolean isLeftX) {
        super(left, operator, right);
        this.isLeftX = isLeftX;
    }

    @Override
    public String getText() {
        if (isLeftX) {
            return "x" + " " + operator.getSymbol() + " " + left + " = " + right;
        } else {
            return left + " " + operator.getSymbol() + " " + "x" + " = " + right;
        }
    }

    @Override
    public boolean isInvalid() {
        if (operator == MathOperation.DIVISION) {
            return left == 0 || (!isLeftX && right == 0);
        }
        if (operator == MathOperation.MULTIPLICATION) {
            return left == 0;
        }
        return false;
    }

    @Override
    public double computeAnswer() {
        if (isLeftX) {
            return switch (operator) {
                case SUM -> right - left;
                case DIFFERENCE -> left + right;
                case MULTIPLICATION -> (double) right / left;
                case DIVISION -> left * right;
            };
        } else {
            return switch (operator) {
                case SUM -> right - left;
                case DIFFERENCE -> left - right;
                case MULTIPLICATION -> (double) right / left;
                case DIVISION -> (double) left / right;
            };
        }
    }
}
