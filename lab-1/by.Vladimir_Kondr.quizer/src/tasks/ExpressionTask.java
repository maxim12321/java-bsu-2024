package tasks;

import tasks.math.AbstractMathTask;
import tasks.math.MathTask;

public class ExpressionTask extends AbstractMathTask {

    public ExpressionTask(int a, int b, Operation op) {
        super(op, a, b);
    }

    @Override
    protected int computeAnswer() {
        try {
            return op.perform(left, right);
        } catch (ArithmeticException e) {
            throw new IllegalArgumentException("Invalid operation: " + e.getMessage());
        }
    }

    @Override
    public boolean isValid() {
        return !(this.op == Operation.DIVISION && right == 0);
    }

    /**
     * @return текст задания
     */
    @Override
    public String getText() {
        String text = left + " " + op.getSymbol() + " " + right + " = ?";
        text += String.format(" (если ответ нецелый, укажите его с точностью до %d-х знаков после запятой, с округлением вниз)", MathTask.Operation.getAccuracy());
        return text;
    }
}