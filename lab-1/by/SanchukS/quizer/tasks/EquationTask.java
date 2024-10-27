package by.SanchukS.quizer.tasks;

import by.SanchukS.quizer.Expression;
import by.SanchukS.quizer.Result;
import by.SanchukS.quizer.Task;

public class EquationTask implements Task {
    Expression expression;
    private final boolean variableRight;

    public EquationTask(Expression expression, boolean variableRight) {
        this.expression = expression;
        this.variableRight = variableRight;
    }

    @Override
    public String getText() {
        if (variableRight) {
            return expression.getNumber(0)
                    + expression.getOperation()
                    + "x="
                    + expression.getNumber(2);
        } else {
            return "x"
                    + expression.getOperation()
                    + expression.getNumber(1)
                    + "="
                    + expression.getNumber(2);
        }

    }

    @Override
    public Result validate(String answer) {
        int rightAnswer = variableRight ? expression.getNumber(1) : expression.getNumber(0);
        try {
            int userAnswer = Integer.parseInt(answer);
            return userAnswer == rightAnswer ? Result.OK : Result.WRONG;
        } catch (NumberFormatException e) {
            return Result.INCORRECT_INPUT;
        }
    }
}
