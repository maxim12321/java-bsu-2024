package by.mmaxemm.quizer.tasks.math;

public class ExpressionTask extends AbstractMathTask {

    @Override
    public double calculateRightAnswer() {
        return switch(operator) {
            case ADDITION -> num1 + num2;
            case SUBSTRACTION -> num1 - num2;
            case MULTIPLICATION -> num1 * num2;
            case DIVISION -> (double) num1 / num2;
        };
    }

    public ExpressionTask(int num1, int num2, Operation operator) {
        super(num1, num2, operator);
    }

    @Override
    //<num1><operator><num2>=?
    public String getText() {
        return num1 + operator.getSymbol() + num2 + "=?";
    }
}