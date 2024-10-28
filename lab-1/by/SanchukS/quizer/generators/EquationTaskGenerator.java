package by.SanchukS.quizer.generators;

import by.SanchukS.quizer.TaskGenerator;
import by.SanchukS.quizer.generators.math.AbstractMathTaskGenerator;
import by.SanchukS.quizer.tasks.EquationTask;
import by.SanchukS.quizer.tasks.math.MathTask;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class EquationTaskGenerator extends AbstractMathTaskGenerator {
    private Random random = new Random();

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
        super(
                minNumber,
                maxNumber,
                generateSum,
                generateDifference,
                generateMultiplication,
                generateDivision
        );
    }

    /**
     * return задание типа {@link EquationTask}
     */
    public MathTask generate() {
        return new EquationTask(generateExpression(), random.nextBoolean());
    }
}