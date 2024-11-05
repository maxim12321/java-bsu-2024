package tasks;

import core.Result;
import tasks.math.AbstractMathTask;
import tasks.math.MathTask;

public class ExpressionTask extends AbstractMathTask {
    private final int answer;

    public ExpressionTask(int a, int b, Operation op) throws IllegalArgumentException {
        super(op, a, b);
        this.answer = computeAnswer();
    }

    @Override
    protected int computeAnswer() throws IllegalArgumentException {
        try {
            return op.perform(left, right);
        } catch (ArithmeticException e) {
            throw new IllegalArgumentException("Invalid operation: " + e.getMessage());
        }
    }

    @Override
    public boolean isValid() {
        return !(this.op == Operation.DIVISION && right == 0);
    }

    /**
     * @return текст задания
     */
    @Override
    public String getText() {
        String text = left + " " + op.getSymbol() + " " + right + " = ?";
        text += String.format(" (если ответ нецелый, укажите его с точностью до %d-х знаков после запятой, с округлением вниз)", MathTask.Operation.getAccuracy());
        return text;
    }

    /**
     * Проверяет ответ на задание и возвращает результат
     *
     * @param  answer ответ на задание
     * @return        результат ответа
     * @see           Result
     */
    public Result validate(String answer) {
        try {
            double ans = Double.parseDouble(answer);
            return (this.answer == ((int) (ans * MathTask.Operation.pow10()))) ? Result.OK : Result.WRONG;

        } catch (NumberFormatException e) {
            return Result.INCORRECT_INPUT;
        }
    }
}