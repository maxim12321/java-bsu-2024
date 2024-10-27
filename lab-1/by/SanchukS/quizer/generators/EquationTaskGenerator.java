package by.SanchukS.quizer.generators;

import by.SanchukS.quizer.TaskGenerator;
import by.SanchukS.quizer.tasks.EquationTask;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class EquationTaskGenerator implements TaskGenerator {
    private ExpressionGenerator expressionGenerator;
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
        List<String> operations = new ArrayList<>();
        if (generateSum) operations.add("+");
        if (generateDifference) operations.add("-");
        if (generateMultiplication) operations.add("*");
        if (generateDivision) operations.add("/");
        this.expressionGenerator = new ExpressionGenerator(minNumber, maxNumber, operations);
    }

    /**
     * return задание типа {@link EquationTask}
     */
    public EquationTask generate() {
        return new EquationTask(expressionGenerator.generateExpression(), random.nextBoolean());
    }
}