package KlimovMikh.quizer.tasks.math;

import KlimovMikh.quizer.Result;

public class ExpressionTask extends AbstractMathTask {
    public ExpressionTask(int number1, int number2, Operation operation) {
        super(number1, number2, operation);
    }

    @Override
    public String getText() {
        return number1 + operation.toString() + number2 + "=?";
    }

    @Override
    public Result validate(String answer) {
        try {
            Integer.parseInt(answer);
        } catch (NumberFormatException e) {
            return Result.INCORRECT_INPUT;
        }
        if(Integer.valueOf(answer).equals(operation.apply(false, number1, number2))) {
            return Result.OK;
        }
        return Result.WRONG;
    }
}
