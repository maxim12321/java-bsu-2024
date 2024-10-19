package by.KirillBukato.quizer.tasks.math;

public class EquationTask extends AbstractMathTask {

    public EquationTask(int left, MathOperation operator, int right, boolean isXLeft) {
        super(left, operator, right);
        this.isXLeft = isXLeft;
    }

    @Override
    public String getText() {
        if (isXLeft) {
            return "x" + " " + operator.getSymbol() + " " + left + " = " + right;
        } else {
            return left + " " + operator.getSymbol() + " " + "x" + " = " + right;
        }
    }

    /**
     * Проверка на невалидные уравнение вида 0*x = b, 0/x = b, x/0 = b, a/x = 0
     */
    @Override
    public boolean isValid() {
        if (operator == MathOperation.DIVIDE) {
            return left != 0 && (isXLeft || right != 0);
        }
        if (operator == MathOperation.MULTIPLY) {
            return left != 0;
        }
        return true;
    }

    @Override
    public double computeAnswer() {
        if (isXLeft) {
            return switch (operator) {
                case ADD -> right - left; //x + a = b
                case SUBTRACT -> left + right; //x - a = b
                case MULTIPLY -> (double) right / left; //x * a = b
                case DIVIDE -> left * right; // x / a = b
            };
        } else {
            return switch (operator) {
                case ADD -> right - left; //a + x = b
                case SUBTRACT -> left - right; //a - x = b
                case MULTIPLY -> (double) right / left; //a * x = b
                case DIVIDE -> (double) left / right; // a / x = b
            };
        }
    }

    private final boolean isXLeft;
}
