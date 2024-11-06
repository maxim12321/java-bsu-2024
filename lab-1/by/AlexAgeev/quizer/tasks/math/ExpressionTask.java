package by.AlexAgeev.quizer.tasks.math;

import by.AlexAgeev.quizer.Result;
import by.AlexAgeev.quizer.generators.math.EquationTaskGenerator;

public class ExpressionTask extends AbstractMathTask {
    public ExpressionTask(int first_num, int second_num, Operators operation) {
        super(2 ,first_num, second_num, operation);
    }

    @Override
    public String getText() {
        return this.first_num + " " + OperatorToString(this.operator) + " " + this.second_num + " = ?";
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
