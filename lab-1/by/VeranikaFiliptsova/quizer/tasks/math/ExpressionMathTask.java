package by.VeranikaFiliptsova.quizer.tasks.math;

import by.VeranikaFiliptsova.quizer.Result;

public class ExpressionMathTask extends AbstractMathTask {

    public ExpressionMathTask(int n1, Operation op, int n2) {
        super(n1, op, n2);
    }

    public int calculate() {
        return switch(operation) {
            case SUM -> num1 + num2;
            case DIFF -> num1 - num2;
            case MUL -> num1 * num2;
            case DIV -> num1 / num2;
        };
    }

    @Override
    public String getText() {
        return num1 + Operation.myValueOf(operation) + myValueOf(num2) + "=";
    }

    @Override
    public Result validate(String answer) {
        return super.validate(answer);
    }

}
