package by.mmaxemm.quizer.tasks.math;

public class EquationTask extends AbstractMathTask {

    @Override
    public double calculateRightAnswer() {
        return switch(operator) {
            case ADDITION -> num2 - num1;
            case SUBSTRACTION -> num1 + num1;
            case MULTIPLICATION -> (double) num2 / num1;
            case DIVISION -> num1 * num1;
        };
    }

    public EquationTask(int num1, int num2, Operation operator) {
        super(num1, num2, operator);
    }

    @Override
    public String getText() {
        //<num1><operator>x=<num2>
        return num1 + operator.getSymbol() + "x=" + num2;
    }
}