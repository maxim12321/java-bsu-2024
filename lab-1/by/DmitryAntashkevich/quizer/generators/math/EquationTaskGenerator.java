package by.DmitryAntashkevich.quizer.generators.math;

import by.DmitryAntashkevich.quizer.tasks.math.EquationTask;
import by.DmitryAntashkevich.quizer.tasks.math.MathTask.Operation;

import java.util.EnumSet;

public class EquationTaskGenerator extends AbstractMathTaskGenerator<EquationTask> {
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

    /**
     * return задание типа {@link EquationTask}
     */
    public EquationTask generate() {
        Operation operation = generateOperation();
        boolean isXOnLeft = generateBool();
        return switch (operation) {
            case ADDITION, SUBTRACTION -> new EquationTask(generateNumber(), operation, generateNumber(), isXOnLeft);
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
                int lhs = generateNonZeroMultiple(rhs);
                yield new EquationTask(lhs, operation, rhs, false);
            }
        };
    }
}