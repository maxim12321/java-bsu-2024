package by.pkasila.quizer.generators;

import by.pkasila.quizer.TaskGenerator;
import by.pkasila.quizer.exceptions.QuizException;
import by.pkasila.quizer.generators.math.MathTaskGenerator;
import by.pkasila.quizer.tasks.EquationTask;

public class EquationTaskGenerator<T extends Number> implements TaskGenerator, MathTaskGenerator {
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
        throw new QuizException("not implemented");
    }

    /**
     * @return
     */
    @Override
    public int getMinNumber() {
        return 0;
    }

    /**
     * @return
     */
    @Override
    public int getMaxNumber() {
        return 0;
    }
}
