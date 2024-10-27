package by.maxsavitsky.quizer.tasks;

import by.maxsavitsky.quizer.tasks.math.AbstractMathTask;

public class ExpressionTask extends AbstractMathTask {

    private final String text;

    public ExpressionTask(int number1, int number2, char operator) {
        super(evaluate(number1, number2, operator));
        text = Integer.toString(number1) + operator + number2;
    }

    @Override
    public String getText() {
        return text;
    }

    private static int evaluate(int number1, int number2, char operator) {
        if (operator == '+') {
            return number1 + number2;
        }
        if (operator == '-') {
            return number1 - number2;
        } if (operator == '*') {
            return number1 * number2;
        } if (operator == '/') {
            return number1 / number2;
        }
        throw new IllegalArgumentException("Unknown operator '" + operator + "'");
    }

}
