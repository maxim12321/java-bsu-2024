package by.DmitryAntashkevich.quizer.tasks.math;

import by.DmitryAntashkevich.quizer.exceptions.InvalidTaskException;

public class EquationTask extends AbstractMathTask {
    public EquationTask(int operand, MathTask.Operation operation, int result, boolean isXOnLeft) {
        super(operand, operation, result);
        this.isXOnLeft = isXOnLeft;
        validate();
    }

    public void validate() {
        if (lhs == 0) {
            throw new InvalidTaskException("lhs = 0");
        }
        if (operation.equals(Operation.DIVISION) && !isXOnLeft && lhs % rhs != 0) {
            throw new InvalidTaskException(String.format("The equation is " + getText() + String.format(", but %d mod %d != 0", lhs, rhs)));
        }
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
