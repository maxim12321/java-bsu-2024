package by.TimurTahil.quizer.tasks.math;

import by.TimurTahil.quizer.Result;

public class EquationTask extends AbstractMathTask {
    public EquationTask(int positionOfX, int firstNumber, int secondNumber, Operators operation) {
        super(positionOfX, firstNumber, secondNumber, operation);
    }

    @Override
    public String getText() {
        String text;
        if (positionOfX == 0) {
            text = "x " + OperatorToString(this.operator) + " " + this.firstNumber + " = " + this.secondNumber;
        } else if (positionOfX == 1) {
            text = this.firstNumber + " " + OperatorToString(this.operator) + " x = " + this.secondNumber;
        } else {
            throw new IllegalArgumentException("wrong value of positionOfX: " + this.positionOfX);
        }
        return text;
    }

    @Override
    public Result validate(String answer) {
        try {
            double userAnswer = Double.parseDouble(answer);
        } catch (NumberFormatException e) {
            return Result.INCORRECT_INPUT;
        }
        if ((Math.abs((GetAnswer() - Double.parseDouble(answer))) <= 0.0001)) {
            return Result.OK;
        } else {
            return Result.WRONG;
        }
    }

}
