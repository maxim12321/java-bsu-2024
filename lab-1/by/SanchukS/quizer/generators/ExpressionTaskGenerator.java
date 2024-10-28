package by.SanchukS.quizer.generators;

import by.SanchukS.quizer.Expression;
import by.SanchukS.quizer.Task;
import by.SanchukS.quizer.TaskGenerator;
import by.SanchukS.quizer.generators.math.AbstractMathTaskGenerator;
import by.SanchukS.quizer.tasks.ExpressionTask;
import by.SanchukS.quizer.tasks.math.MathTask;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class ExpressionTaskGenerator extends AbstractMathTaskGenerator {
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
     * return задание типа {@link ExpressionTask}
     */
    @Override
    public MathTask generate() {
        return new ExpressionTask(generateExpression());
    }
}