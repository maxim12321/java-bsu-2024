package by.DmitryAntashkevich.quizer.tasks.math;

public class EquationTask extends AbstractMathTask {
    public EquationTask(int operand, MathTask.Operation operation, int result, boolean isXOnLeft) {
        super(operand, operation, result);
        this.isXOnLeft = isXOnLeft;
    }

    @Override
    public boolean isValid() {
        return switch (operation) {
            case MULTIPLICATION -> lhs != 0;
            case DIVISION -> !(lhs == 0 || (!isXOnLeft && lhs % rhs != 0));
            default -> true;
        };
    }

    @Override
    public int getAnswer() {
        return switch (operation) {
            case ADDITION -> rhs - lhs;
            case SUBTRACTION -> isXOnLeft ? lhs + rhs : lhs - rhs;
            case MULTIPLICATION -> rhs / lhs;
            case DIVISION -> isXOnLeft ? lhs * rhs : lhs / rhs;
        };
    }

    @Override
    public String getText() {
        return (isXOnLeft ? "x" + operation.GetSymbol() + toString(lhs)
                : toString(lhs) + operation.GetSymbol() + "x") + "=" + String.valueOf(rhs);
    }

    private final boolean isXOnLeft;
}
