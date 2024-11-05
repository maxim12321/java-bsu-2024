package by.merinovvvv.quizer.generators;

import by.merinovvvv.quizer.generators.math.AbstractMathTaskGenerator;
import by.merinovvvv.quizer.generators.math.MathTaskGenerator;
import by.merinovvvv.quizer.tasks.EquationTask;
import by.merinovvvv.quizer.tasks.math.MathTask;

public class EquationTaskGenerator extends AbstractMathTaskGenerator implements MathTaskGenerator {
    /**
     * @param minNumber              минимальное число
     * @param maxNumber              максимальное число
     * @param generateSum            разрешить генерацию с оператором +
     * @param generateDifference     разрешить генерацию с оператором -
     * @param generateMultiplication разрешить генерацию с оператором *
     * @param generateDivision       разрешить генерацию с оператором /
     */

    private final int minNumber;
    private final int maxNumber;
    private final MathTask.Operation operation;
    private final boolean monkey;

    public EquationTaskGenerator(
            int minNumber,
            int maxNumber,
            MathTask.Operation operation
    ) {
        if (minNumber > maxNumber) {
            throw new IllegalArgumentException("minNumber can't be greater than maxNumber.");
        }
        this.minNumber = minNumber;
        this.maxNumber = maxNumber;
        this.operation = operation;
        monkey = false;
    }

    /**
     * return задание типа {@link EquationTask}
     */
    @Override
    public EquationTask generate() {
        Object[] array = generateMathTask(maxNumber, minNumber, operation, monkey);
        return new EquationTask((Integer) array[0], (Integer) array[1], String.valueOf(array[2]), (Integer) array[3]);
    }

    @Override
    public int getMinNumber() {
        return minNumber;
    }

    @Override
    public int getMaxNumber() {
        return maxNumber;
    }
}