package by.KirillBukato.quizer.tasks.math;

import java.util.Objects;

public class ExpressionTask extends AbstractMathTask {

    public ExpressionTask(int left, MathTask.Operation operator, int right) {
        super(left, operator, right);
    }

    @Override
    public boolean isValid() {
        return operator != Operation.DIVIDE || right != 0;
    }

    @Override
    public String getText() {
        return Objects.toString(left) + stringOperator(operator) + Objects.toString(right) + "=?";
    }

    @Override
    public double ComputeAnswer() {
        return switch (operator) {
            case ADD -> left + right;
            case SUBTRACT -> left - right;
            case MULTIPLY -> left * right;
            case DIVIDE -> (double) left / right;
        };
    }
}
