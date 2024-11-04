package by.KirillBukato.quizer.tasks.math;

import by.KirillBukato.quizer.Result;

/**
 * Абстрактный класс для всех математических задач
 */
public abstract class AbstractMathTask implements MathTask {

    public AbstractMathTask(int left, MathOperation operator, int right) {
        this.left = left;
        this.operator = operator;
        this.right = right;
    }

    /**
     * Проверка ответа использует метод computeAnswer() который вычисляет правильный ответ.
     * Далее ответ пользователя сравнивается с правильным.
     */
    @Override
    public Result validate(String answer) {
        double correctAnswer = computeAnswer();
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
    protected final MathOperation operator;
    protected final int right;
}
