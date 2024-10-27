package by.AlbertRadoshko.quizer.tasks.math;

import by.AlbertRadoshko.quizer.exceptions.InvalidTaskException;

public class ExpressionTask extends AbstractMathTask {
    public ExpressionTask(int x, int y, MathTask.Operation op) {
        super(x, y, op);
    }

    public void validate() {
        if (op.equals(Operation.DIVISION) && rhs == 0) {
            throw new InvalidTaskException(getText() + "is not solvable");
        }
        if (op.equals(Operation.DIVISION) && lhs % rhs != 0) {
            throw new InvalidTaskException(getText() + "is not solvable");
        }
    }

    @Override
    public String getText() {
        return "Решите в целых числах: " + str(lhs) + op.getSymbol() + str(rhs);
    }

    @Override
    public int getAnswer() {
        return op.compute(lhs, rhs);
    }
}
