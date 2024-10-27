package by.request3301.quizer.tasks.math;

import by.request3301.quizer.Result;

public class ExpressionMathTask extends AbstractMathTask {
    public ExpressionMathTask(String lhs, String rhs) {
        super(lhs, rhs);
    }

    @Override
    public Result validate(String answer) {
        int x;
        try {
            x = Integer.parseInt(answer);
        } catch (NumberFormatException e) {
            return Result.INCORRECT_INPUT;
        }
        return checkEqual(lhs, String.valueOf(x)) ? Result.OK : Result.WRONG;
    }
}
