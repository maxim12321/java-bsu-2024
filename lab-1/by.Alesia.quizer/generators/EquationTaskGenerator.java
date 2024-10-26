package by.Alesia.quizer.generators;

import by.Alesia.quizer.generators.math.AbstractMathTaskGenerator;
import by.Alesia.quizer.tasks.EquationTask;


public class EquationTaskGenerator extends AbstractMathTaskGenerator<EquationTask> {
    /**
     * @param minNumber              минимальное число
     * @param maxNumber              максимальное число
     * @param generateSum            разрешить генерацию с оператором +
     * @param generateDifference     разрешить генерацию с оператором -
     * @param generateMultiplication разрешить генерацию с оператором *
     * @param generateDivision       разрешить генерацию с оператором /
     */
    public EquationTaskGenerator(
            int minNumber,
            int maxNumber,
            boolean generateSum,
            boolean generateDifference,
            boolean generateMultiplication,
            boolean generateDivision
    ) {
        super(generateSum, generateDifference, generateMultiplication, generateDivision, minNumber, maxNumber);
    }

    /**
     * return задание типа {@link EquationTask}
     */
    public EquationTask generate() {
        return new EquationTask(genNum(), genNum(), genOperation());
    }
}