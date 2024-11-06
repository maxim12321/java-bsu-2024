package by.AlexAgeev.quizer.tasks.math;

import by.AlexAgeev.quizer.Result;
import by.AlexAgeev.quizer.generators.math.EquationTaskGenerator;

public class EquationTask extends AbstractMathTask {
    public EquationTask(int x_pos, int first_num, int second_num, Operators operation) {
        super(x_pos, first_num, second_num, operation);
    }

    @Override
    public String getText() {
        String text;
        if (x_pos == 0) {
            text = "x " + OperatorToString(this.operator) + " " + this.first_num + " = " + this.second_num;
        } else {
            text = this.first_num + " " + OperatorToString(this.operator) + " x = " + this.second_num;
        }
        return text;
    }

    @Override
    public Result validate(String answer) {
        // проверить на корректный ввод
        try {
            double userAnswer = Double.parseDouble(answer);
        } catch (NumberFormatException e) {
            return Result.INCORRECT_INPUT;
        }
        if ((Math.abs((Answer() - Double.parseDouble(answer))) <= 0.001)) {
            return Result.OK;
        } else {
            return Result.WRONG;
        }
    }

}
