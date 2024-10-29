package by.SanchukS.quizer.generators.math;

import by.SanchukS.quizer.Expression;
import by.SanchukS.quizer.generators.ExpressionGenerator;
import by.SanchukS.quizer.tasks.math.AbstractMathTask;
import by.SanchukS.quizer.tasks.math.MathTask;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractMathTaskGenerator implements MathTaskGenerator {
    private final ExpressionGenerator expressionGenerator;

    /**
     * @param minNumber              минимальное число
     * @param maxNumber              максимальное число
     * @param generateSum            разрешить генерацию с оператором +
     * @param generateDifference     разрешить генерацию с оператором -
     * @param generateMultiplication разрешить генерацию с оператором *
     * @param generateDivision       разрешить генерацию с оператором /
     */
    protected AbstractMathTaskGenerator(
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

    public abstract MathTask generate();

    protected Expression generateExpression() {
        return expressionGenerator.generateExpression();
    }

    @Override
    public int getMaxNumber() {
        return expressionGenerator.getMaxNumber();
    }

    @Override
    public int getMinNumber() {
        return expressionGenerator.getMinNumber();
    }
}
