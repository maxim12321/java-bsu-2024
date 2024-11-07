package by.pkasila.quizer.tasks.math;

import by.pkasila.quizer.common.MathOperation;

import java.util.Objects;

public abstract class AbstractExpressionTask extends AbstractMathTask {

    public AbstractExpressionTask(int left, MathOperation operator, int right) {
        super(left, operator, right);
    }

    @Override
    public boolean isInvalid() {
        return operator == MathOperation.DIVISION && right == 0;
    }

    @Override
    public double computeAnswer() {
        return switch (operator) {
            case SUM -> left + right;
            case DIFFERENCE -> left - right;
            case MULTIPLICATION -> left * right;
            case DIVISION -> (double) left / right;
        };
    }

    public String getQuestion() {
        return Objects.toString(left) + " " + operator.getSymbol() + " " + Objects.toString(right) + " = ?";
    }
}
