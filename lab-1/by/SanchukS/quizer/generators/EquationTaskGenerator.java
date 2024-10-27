package by.SanchukS.quizer.generators;

import by.SanchukS.quizer.TaskGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class EquationTaskGenerator implements TaskGenerator {
    private final Random random = new Random();
    private final int minNumber;
    private final int maxNumber;
    private final List<String> operations;

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
        this.minNumber = minNumber;
        this.maxNumber = maxNumber;

        List<String> operations = new ArrayList<>();
        if (generateSum) operations.add("+");
        if (generateDifference) operations.add("-");
        if (generateMultiplication) operations.add("*");
        if (generateDivision) operations.add("/");
        this.operations = operations;
    }

    /**
     * return задание типа {@link EquationTask}
     */
    EquationTask generate() {
        // ...
    }
}