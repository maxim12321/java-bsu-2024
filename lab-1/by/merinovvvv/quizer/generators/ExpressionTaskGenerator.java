package by.merinovvvv.quizer.generators;

import by.merinovvvv.quizer.generators.math.AbstractMathTaskGenerator;
import by.merinovvvv.quizer.generators.math.MathTaskGenerator;
import by.merinovvvv.quizer.tasks.ExpressionTask;
import by.merinovvvv.quizer.tasks.math.MathTask;;

public class ExpressionTaskGenerator extends AbstractMathTaskGenerator implements MathTaskGenerator {
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

    public ExpressionTaskGenerator(
            int minNumber,
            int maxNumber,
            MathTask.Operation operation
    ) {
        if (minNumber > maxNumber) {
            throw new IllegalArgumentException("minNumber can't be greater than maxNumber");
        }
        this.minNumber = minNumber;
        this.maxNumber = maxNumber;
        this.operation = operation;
        monkey = true;
    }

    /**
     * return задание типа {@link ExpressionTask}
     */

    @Override
    public ExpressionTask generate() {
        Object[] array = generateMathTask(maxNumber, minNumber, operation, monkey);
        return new ExpressionTask((Integer) array[0], (Integer) array[1], String.valueOf(array[2]));
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
