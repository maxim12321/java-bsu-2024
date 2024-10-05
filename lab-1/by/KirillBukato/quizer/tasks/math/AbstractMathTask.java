package by.KirillBukato.quizer.tasks.math;

import by.KirillBukato.quizer.Result;

public abstract class AbstractMathTask implements MathTask {

    public AbstractMathTask(int left, MathTask.Operation operator, int right) {
        this.left = left;
        this.operator = operator;
        this.right = right;
    }

    @Override
    public Result validate(String answer) {
        double correctAnswer = ComputeAnswer();
        double userAnswer;
        try {
            userAnswer = Double.parseDouble(answer);
        } catch (NumberFormatException e) {
            return Result.INCORRECT_INPUT;
        }
        if (Math.abs(userAnswer - correctAnswer) < 0.001) {
            return Result.OK;
        } else {
            return Result.WRONG;
        }
    }

    protected final int left;
    protected final MathTask.Operation operator;
    protected final int right;
}
