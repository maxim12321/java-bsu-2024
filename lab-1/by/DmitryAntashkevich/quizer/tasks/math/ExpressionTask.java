package by.DmitryAntashkevich.quizer.tasks.math;

public class ExpressionTask extends AbstractMathTask {
    public ExpressionTask(int lhs, MathTask.Operation operation, int rhs) {
        super(lhs, operation, rhs);
    }

    @Override
    public boolean isValid() {
        return operation != Operation.DIVISION || (rhs != 0 && lhs % rhs == 0);
    }

    @Override
    public int getAnswer() {
        return operation.Compute(lhs, rhs);
    }

    @Override
    public String getText() {
        return toString(lhs) + operation.GetSymbol() + toString(rhs) + "=?";
    }
}
