package by.DmitryAntashkevich.quizer.generators.math;

import by.DmitryAntashkevich.quizer.tasks.math.EquationTask;
import by.DmitryAntashkevich.quizer.tasks.math.MathTask.Operation;

import java.util.EnumSet;

public class EquationTaskGenerator extends AbstractMathTaskGenerator {
    /**
     * @param minNumber         минимальное число
     * @param maxNumber         максимальное число
     * @param allowedOperations разрешенные операции (+, -, *, /)
     */
    public EquationTaskGenerator(
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
    public EquationTaskGenerator(
            int minNumber,
            int maxNumber
    ) {
        super(minNumber, maxNumber);
    }

    @Override
    public boolean isValid() {
        if (allowedOperations.isEmpty() || minNumber > maxNumber) {
            return false;
        }
        if (allowedOperations.equals(EnumSet.of(Operation.MULTIPLICATION, Operation.DIVISION))) {
            return minNumber != 0 || maxNumber != 0;
        }
        return true;
    }

    /**
     * return задание типа {@link EquationTask}
     */
    public EquationTask generate() {
        Operation operation = generateOperation();
        boolean isXOnLeft = generateBool();
        for (int i = 0; i < tryCount; i++) {
            EquationTask task = switch (operation) {
                case ADDITION, SUBTRACTION ->
                        new EquationTask(generateNumber(), operation, generateNumber(), isXOnLeft);
                case MULTIPLICATION -> {
                    int lhs = generateNonZeroNumber();
                    int rhs = generateMultiple(lhs);
                    yield new EquationTask(lhs, operation, rhs, isXOnLeft);
                }
                case DIVISION -> {
                    if (isXOnLeft) {
                        yield new EquationTask(generateNonZeroNumber(), operation, generateNumber(), true);
                    }
                    int rhs = generateNonZeroNumber();
                    int lhs = generateMultiple(rhs);
                    yield new EquationTask(lhs, operation, rhs, false);
                }
            };
            if (task.isValid()) {
                return task;
            }
        }
        throw new RuntimeException("failed to generate a task");
    }
}