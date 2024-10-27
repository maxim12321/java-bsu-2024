package by.dilik14.quizer.tasks.math;

public class EquationTask extends AbstractMathTask {
    /**
     * @param operand     операнд в lhs
     * @param operation   операция в lhs
     * @param result      число в rhs
     * @param XFirst      идёт ли x первым в lhs
     */
    public EquationTask(int operand, MathTask.Operation operation, int result, boolean XFirst) {
        super(operand, operation, result);
        this.XFirst = XFirst;
        check();
    }

    public void check() {
        if (operation.equals(Operation.MULTIPLICATION) && lhs == 0) {
            throw new IllegalArgumentException("Infinite solutions");
        }

        if (operation.equals(Operation.DIVISION) && lhs == 0 && !XFirst) {
            throw new IllegalArgumentException("Zero division");
        }

        if (operation.equals(Operation.MULTIPLICATION) && rhs % lhs != 0) {
            throw new IllegalArgumentException("No int solution");
        }

        if (operation.equals(Operation.DIVISION) && !XFirst && lhs % rhs != 0) {
            throw new IllegalArgumentException("No int solution");
        }
    }

    @Override
    public int computeAnswer() {
        return switch (operation) {
            case ADDITION -> rhs - lhs;
            case SUBTRACTION -> XFirst ? lhs + rhs : lhs - rhs;
            case MULTIPLICATION -> rhs / lhs;
            case DIVISION -> XFirst ? lhs * rhs : lhs / rhs;
        };
    }

    @Override
    public String getText() {
        if (XFirst) {
            return "x" + operation.GetSymbol() + toString(lhs) + " = " + toString(rhs);
        } else {
            return toString(lhs) + operation.GetSymbol() + "x" + " = " + toString(rhs);
        }
    }

    private final boolean XFirst;
}
