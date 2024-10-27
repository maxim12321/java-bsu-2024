package by.MikhailNaumovich.quizer.tasks.math;

import by.MikhailNaumovich.quizer.Result;


public abstract class AbstractMathTask implements MathTask {

    public AbstractMathTask(int left, MathEnum operator, int right) {
        this.left = left;
        this.operator = operator;
        this.right = right;
    }

    @Override
    public String getText() {
        return left + " " + operator.getSymbol() + " " + right + " = ";
    }

    public Result validate(String answer) {
        if (answer == null) {
            throw new IllegalArgumentException("Answer is null");
        }
        double userAnswer;
        try { 
            userAnswer = Double.parseDouble(answer);
        } catch (NumberFormatException e) {
            return Result.INCORRECT_INPUT;
        }

        double correctAnswer = compute();
        if (Math.abs(userAnswer - correctAnswer) < eps) {
            return Result.OK;
        } else {
            return Result.WRONG;
        }
    }

    protected final int left;
    protected final MathEnum operator;
    protected final int right;

    protected final double eps = 0.0001;
}


