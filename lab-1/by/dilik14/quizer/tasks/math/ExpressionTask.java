package by.dilik14.quizer.tasks.math;

public class ExpressionTask extends AbstractMathTask {
    /**
     * @param lhs         операнд слева от операции
     * @param operation   операция
     * @param rhs         операнд справа от операции
     */
    public ExpressionTask(int lhs, MathTask.Operation operation, int rhs) {
        super(lhs, operation, rhs);
        check();
    }

    public void check() {
        if (operation == Operation.DIVISION && rhs == 0) {
            throw new IllegalArgumentException("Zero division");
        }
    }

    @Override
    public int computeAnswer() {
        return operation.Compute(lhs, rhs);
    }

    @Override
    public String getText() {
        return toString(lhs) + operation.GetSymbol() + toString(rhs) + " = ?";
    }
}
