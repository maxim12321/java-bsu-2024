package by.TimurTahil.quizer.tasks.math;

import by.TimurTahil.quizer.Result;

public class ExpressionTask extends AbstractMathTask {
    public ExpressionTask(int firstNumber, int secondNumber, Operators operation) {
        super(-1, firstNumber, secondNumber, operation);
    }

    @Override
    public String getText() {
        return this.firstNumber + " " + OperatorToString(this.operator) + " " + this.secondNumber + " = ?";
    }

    @Override
    public Result validate(String answer) {
        try {
            double userAnswer = Double.parseDouble(answer);
        } catch (NumberFormatException e) {
            return Result.INCORRECT_INPUT;
        }
        if ((Math.abs((GetAnswer() - Double.parseDouble(answer))) <= 0.001)) {
            return Result.OK;
        } else {
            return Result.WRONG;
        }
    }
}
