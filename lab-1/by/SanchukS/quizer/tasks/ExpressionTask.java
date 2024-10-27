package by.SanchukS.quizer.tasks;

import by.SanchukS.quizer.Expression;
import by.SanchukS.quizer.Result;
import by.SanchukS.quizer.Task;

public class ExpressionTask implements Task {
    Expression expression;


    public ExpressionTask(Expression expression) {
        this.expression = expression;
    }

    @Override
    public String getText() {
        return expression.getNumber(0)
                + expression.getOperation()
                + expression.getNumber(1)
                + "=?";
    }

    @Override
    public Result validate(String answer) {
        try {
            int userAnswer = Integer.parseInt(answer);
            return userAnswer == expression.getNumber(2) ? Result.OK : Result.WRONG;
        } catch (NumberFormatException e) {
            return Result.INCORRECT_INPUT;
        }
    }
}
