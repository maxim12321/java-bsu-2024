package by.thekeenest.quizer.tasks.math;


public class EquationMathTask extends AbstractMathTask {
    private final boolean isLeftUnknown;

    public EquationMathTask(int knownNumber, int result, Operation operation,
                            boolean isLeftUnknown) {
        super(isLeftUnknown ? result : knownNumber,
                isLeftUnknown ? knownNumber : result,
                operation);
        this.isLeftUnknown = isLeftUnknown;
    }

    @Override
    public String getText() {
        return String.format("Решите уравнение: %s", getExpression());
    }

    @Override
    public String getExpression() {
        if (isLeftUnknown) {
            return String.format("x %s %d = %d",
                    operation.getSymbol(), secondNumber, firstNumber);
        } else {
            return String.format("%d %s x = %d",
                    firstNumber, operation.getSymbol(), secondNumber);
        }
    }

    @Override
    public int getCorrectAnswer() {
        if (isLeftUnknown) {
            switch (operation) {
                case PLUS:
                    return firstNumber - secondNumber;
                case MINUS:
                    return firstNumber + secondNumber;
                case PRODUCT:
                    return firstNumber / secondNumber;
                default:
                    throw new UnsupportedOperationException(
                            "Operation " + operation + " not supported"
                    );
            }
        } else {
            switch (operation) {
                case PLUS:
                    return secondNumber - firstNumber;
                case MINUS:
                    return firstNumber - secondNumber;
                case PRODUCT:
                    return secondNumber / firstNumber;
                default:
                    throw new UnsupportedOperationException(
                            "Operation " + operation + " not supported"
                    );
            }
        }
    }
}