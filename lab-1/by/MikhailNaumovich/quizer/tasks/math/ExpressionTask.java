package by.MikhailNaumovich.quizer.tasks.math;


public class ExpressionTask extends AbstractMathTask {

    public ExpressionTask(int left, MathEnum operator, int right) {
        super(left, operator, right);
    }

    @Override
    public String getText() {
        return left + " " + operator.getSymbol() + " " + right + " = ";
    }

    @Override
    public boolean isValid() {
        return operator != MathEnum.DIVIDE || right != 0;
    }

    @Override
    public double compute() {
        switch (operator) {
            case ADD:
                return left + right;
            case SUBTRACT:
                return left - right;
            case MULTIPLY:
                return left * right;
            case DIVIDE:
                return (double) left / right;
            default:
                throw new IllegalStateException("Unexpected value: " + operator);
        }
    }
}