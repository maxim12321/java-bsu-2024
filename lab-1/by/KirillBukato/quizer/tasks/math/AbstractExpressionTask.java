package by.KirillBukato.quizer.tasks.math;

import java.util.Objects;

public abstract class AbstractExpressionTask extends AbstractMathTask{

    public AbstractExpressionTask(int left, MathTask.Operation operator, int right) {
        super(left, operator, right);
    }

    @Override
    public boolean isValid() {
        return operator != Operation.DIVIDE || right != 0;
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

    public String getQuestion() {
        return Objects.toString(left) + stringOperator(operator) + Objects.toString(right) + "=?";
    }
}
