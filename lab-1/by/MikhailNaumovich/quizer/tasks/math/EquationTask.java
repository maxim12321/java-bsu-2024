package by.MikhailNaumovich.quizer.tasks.math;

public class EquationTask extends AbstractMathTask {
    public EquationTask(int left, MathEnum operator, int right, boolean isFactorLeft) {
        super(left, operator, right);
        this.isFactorLeft = isFactorLeft;
    }

    @Override
    public String getText() {
        if (!isFactorLeft) {
            return "x " + operator.getSymbol() + " " + left + " = " + right;
        } else {
            return left + " " + operator.getSymbol() + " x = " + right;
        }
    }

    @Override
    public boolean isValid() {
        if (operator == MathEnum.MULTIPLY) {
            return left != 0 && right == 0;
        } else if (operator == MathEnum.DIVIDE) {
            return left != 0 && (!isFactorLeft || right != 0);
        }
        return true;
    }

    @Override
    public double compute() {
        switch (operator) {
            case ADD:
                return !isFactorLeft ? right - left : right - left;
            case SUBTRACT:
                return !isFactorLeft ? right + left : left - right;
            case MULTIPLY:
                return !isFactorLeft ? right / left : right / left;
            case DIVIDE:
                return !isFactorLeft ? right * left : left / right;
            default:
                throw new IllegalStateException("Unexpected value: " + operator);
        }
    }
    
    private final boolean isFactorLeft;
}