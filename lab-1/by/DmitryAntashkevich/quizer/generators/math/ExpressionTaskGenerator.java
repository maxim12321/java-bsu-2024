package by.DmitryAntashkevich.quizer.generators.math;

import by.DmitryAntashkevich.quizer.exceptions.GeneratorException;
import by.DmitryAntashkevich.quizer.tasks.math.ExpressionTask;
import by.DmitryAntashkevich.quizer.tasks.math.MathTask.Operation;

import java.util.EnumSet;

public class ExpressionTaskGenerator extends AbstractMathTaskGenerator {
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

//    @Override
//    public boolean isValid() {
//        if (allowedOperations.isEmpty() || minNumber > maxNumber) {
//            return false;
//        }
//        if (allowedOperations.equals(EnumSet.of(Operation.DIVISION))) {
//            return minNumber != 0 || maxNumber != 0;
//        }
//        return true;
//    }

    /**
     * return задание типа {@link ExpressionTask}
     */
    public ExpressionTask generate() {
        Operation operation = generateOperation();
        for (int i = 0; i < tryCount; i++) {
            ExpressionTask task = switch (operation) {
                case ADDITION, SUBTRACTION, MULTIPLICATION ->
                        new ExpressionTask(generateNumber(), operation, generateNumber());
                case DIVISION -> {
                    int rhs = generateNonZeroNumber();
                    int lhs = generateMultiple(rhs);
                    yield new ExpressionTask(lhs, operation, rhs);
                }
            };
            if (task.isValid()) {
                return task;
            }
        }
        throw new GeneratorException("Failed to generate a task");
    }
}