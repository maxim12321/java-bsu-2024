package by.mmaxemm.quizer.generators;

import by.mmaxemm.quizer.tasks.ExpressionTask;

public class ExpressionTaskGenerator implements TaskGenerator {
    /**
     * @param minNumber              минимальное число
     * @param maxNumber              максимальное число
     * @param generateSum            разрешить генерацию с оператором +
     * @param generateDifference     разрешить генерацию с оператором -
     * @param generateMultiplication разрешить генерацию с оператором *
     * @param generateDivision       разрешить генерацию с оператором /
     */
    ExpressionTaskGenerator(
            int minNumber,
            int maxNumber,
            boolean generateSum,
            boolean generateDifference,
            boolean generateMultiplication,
            boolean generateDivision
    ) {
        // ...
    }

    /**
     * return задание типа {@link ExpressionTask}
     */

    @Override
    public ExpressionTask generate() {
        return null;
    }
}