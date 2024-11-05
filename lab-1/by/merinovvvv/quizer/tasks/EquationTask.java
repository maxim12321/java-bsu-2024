package by.merinovvvv.quizer.tasks;

import by.merinovvvv.quizer.Result;
import by.merinovvvv.quizer.tasks.math.AbstractMathTask;
import by.merinovvvv.quizer.tasks.math.MathTask;


public class EquationTask extends AbstractMathTask implements MathTask {

    private final int firstArg;
    private final int secondArg;
    private final int equalsArg;
    private final String operator;

    public EquationTask(int num1, int num2, String operator, int num3) {
        firstArg = num1;
        secondArg = num2;
        equalsArg = num3;
        this.operator = operator;
    }

    @Override
    public String getText() {
        return firstArg + "x" + operator + secondArg + "=" + equalsArg;
    }

    public Result validate(String answer) {
        double tmp;
        double correctAnswer = switch (operator) {
            case "+":
                tmp = (double) (equalsArg - secondArg) / firstArg;
                yield Math.round(tmp * 10000.0) / 10000.0;
            case "-":
                tmp = (double) (equalsArg + secondArg) / firstArg;
                yield Math.round(tmp * 10000.0) / 10000.0;
            case "*":
                tmp = (double) equalsArg / (firstArg * secondArg);
                yield Math.round(tmp * 10000.0) / 10000.0;
            case "/":
                tmp = (double) equalsArg * secondArg / firstArg;
                yield Math.round(tmp * 10000.0) / 10000.0;
            default:
                throw new IllegalArgumentException("Invalid operator.");

        };

        try {
            double userAnswer = Double.parseDouble(answer);
            return userAnswer == correctAnswer ? Result.OK : Result.WRONG;
        } catch (NumberFormatException e) {
            return Result.INCORRECT_INPUT;
        }
    }
}
