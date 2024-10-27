package by.AlbertRadoshko.quizer.tasks.math;

import by.AlbertRadoshko.quizer.Result;
import by.AlbertRadoshko.quizer.exceptions.InvalidTaskException;

public class EquationTask extends AbstractMathTask {
    public enum EquationType {
        LHS,
        RHS
    }

    public EquationTask(int x, int y, Operation op, EquationType type) {
        super(x, y, op);
        this.type = type;
    }

    @Override
    public String getText() {
        if (type == EquationType.LHS) {
            return "Решите в целых числах: x" + op.getSymbol() + str(lhs) + "=" + str(rhs);
        }
        return "Решите в целых числах: " + str(lhs) + op.getSymbol() + "x" + "=" + str(rhs);
    }

    public void validate() {
        if (lhs == 0) {
            throw new InvalidTaskException("lhs = 0");
        }
        if (op.equals(Operation.DIVISION) && type == EquationType.RHS && lhs % rhs != 0) {
            throw new InvalidTaskException(getText() + "is not solvable");
        }
    }

    @Override
    public int getAnswer() {
        if (type == EquationType.LHS) {
            return switch (op) {
                case ADDITION -> rhs - lhs;
                case SUBTRACTION -> rhs + lhs;
                case MULTIPLICATION -> rhs / lhs;
                case DIVISION -> rhs * lhs;
            };
        }
        return switch (op) {
            case ADDITION -> rhs - lhs;
            case SUBTRACTION -> lhs - rhs;
            case MULTIPLICATION -> rhs / lhs;
            case DIVISION -> lhs / rhs;
        };
    }

    EquationType type;
}
