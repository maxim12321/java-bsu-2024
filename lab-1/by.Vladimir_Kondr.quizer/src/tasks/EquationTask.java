package tasks;

import core.Result;
import tasks.math.AbstractMathTask;
import tasks.math.MathTask;

public class EquationTask extends AbstractMathTask {
    private final boolean isXFirst;
    private final int answer;

    public EquationTask(int a, Operation op, int result, boolean isXFirst) throws IllegalArgumentException {
        super(op, a, result);
        this.isXFirst = isXFirst;
        this.answer = computeAnswer();

    }

    @Override
    protected int computeAnswer() {
        if (this.isXFirst || this.op == Operation.ADDITION || this.op == Operation.MULTIPLICATION) {
            return op.getOpposite().perform(right, left);
        }
        return op.perform(left, right);
    }

    @Override
    public boolean isValid() {
        return !(!isXFirst && op == Operation.DIVISION && (right == 0 || left == 0)) &&
                !(op == Operation.DIVISION && left == 0 && isXFirst) &&
                !(op == Operation.MULTIPLICATION && left == 0);
    }

    @Override
    public String getText() {
        String text;
        if (this.isXFirst) {
            text = "x " + op.getSymbol() + " " + left + " = " + right;
        } else {
            text = left + " " + op.getSymbol() + " x" + " = " + right;
        }
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