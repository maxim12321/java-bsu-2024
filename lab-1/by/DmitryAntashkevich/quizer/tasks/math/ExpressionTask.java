package by.DmitryAntashkevich.quizer.tasks.math;

import by.DmitryAntashkevich.quizer.exceptions.InvalidTaskException;

public class ExpressionTask extends AbstractMathTask {
    public ExpressionTask(int lhs, MathTask.Operation operation, int rhs) {
        super(lhs, operation, rhs);
        validate();
    }

    public void validate() {
        if (!operation.equals(Operation.DIVISION)) {
            return;
        }
        if (rhs == 0) {
            throw new InvalidTaskException("rhs = 0");
        }
        if (lhs % rhs != 0) {
            throw new InvalidTaskException("The Expression is " + getText() + String.format(", but %d mod %d != 0", lhs, rhs));
        }
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
