package by.mmaxemm.quizer.generators;
import by.mmaxemm.quizer.tasks.EquationTask;

public class EquationTaskGenerator implements TaskGenerator {
    /**
     * @param minNumber              минимальное число
     * @param maxNumber              максимальное число
     * @param generateSum            разрешить генерацию с оператором +
     * @param generateDifference     разрешить генерацию с оператором -
     * @param generateMultiplication разрешить генерацию с оператором *
     * @param generateDivision       разрешить генерацию с оператором /
     */
    EquationTaskGenerator(
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
     * return задание типа {@link EquationTask}
     */
    public EquationTask generate() {
        return null;
    }
}