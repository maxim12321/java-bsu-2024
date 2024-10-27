package by.DmitryAntashkevich.quizer.generators.math;

import by.DmitryAntashkevich.quizer.tasks.math.ExpressionTask;
import by.DmitryAntashkevich.quizer.tasks.math.MathTask.Operation;

import java.util.EnumSet;

public class ExpressionTaskGenerator extends AbstractMathTaskGenerator<ExpressionTask> {
    /**
     * @param minNumber         минимальное число
     * @param maxNumber         максимальное число
     * @param allowedOperations разрешенные операции (+, -, *, /)
     */
    public ExpressionTaskGenerator(
            int minNumber,
            int maxNumber,
            EnumSet<Operation> allowedOperations
    ) {
        super(minNumber, maxNumber, allowedOperations);
    }

    /**
     * @param minNumber минимальное число
     * @param maxNumber максимальное число
     */
    public ExpressionTaskGenerator(
            int minNumber,
            int maxNumber
    ) {
        super(minNumber, maxNumber);
    }

    /**
     * return задание типа {@link ExpressionTask}
     */
    public ExpressionTask generate() {
        Operation operation = generateOperation();
        return switch (operation) {
            case ADDITION, SUBTRACTION, MULTIPLICATION ->
                    new ExpressionTask(generateNumber(), operation, generateNumber());
            case DIVISION -> {
                int rhs = generateNonZeroNumber();
                int lhs = generateMultiple(rhs);
                yield new ExpressionTask(lhs, operation, rhs);
            }
        };
    }
}