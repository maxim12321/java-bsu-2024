package by.mmaxemm.quizer.tasks.math;

import by.mmaxemm.quizer.Result;

public abstract class AbstractMathTask implements MathTask {
    final protected int num1;
    final protected int num2;
    final protected Operation operator;

    public AbstractMathTask(int num1, int num2, Operation operator) {
        this.num1 = num1;
        this.num2 = num2;
        this.operator = operator;
    }

    public Result validate(String answer) {
        try {
            return Math.abs(Double.parseDouble(answer) - calculateRightAnswer())
                    < 0.001 ? Result.OK : Result.WRONG;
        } catch (NumberFormatException e) {
            return Result.INCORRECT_INPUT;
        }
    }
}
