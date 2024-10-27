package by.dilik14.quizer.generators.math;

import java.util.EnumSet;

import by.dilik14.quizer.tasks.math.ExpressionTask;
import by.dilik14.quizer.tasks.math.MathTask.Operation;

public class ExpressionTaskGenerator extends AbstractMathTaskGenerator<ExpressionTask> {
    /**
     * @param minNumber              минимальное число
     * @param maxNumber              максимальное число
     * @param allowedOperations      разрешенные операции (+, -, *, /)
     */
    public ExpressionTaskGenerator(
            int minNumber,
            int maxNumber,
            EnumSet<Operation> allowedOperations) {
        super(minNumber, maxNumber, allowedOperations);
    }

    /**
     * return задание типа {@link ExpressionTask}
     */
    public ExpressionTask generate() {
        Operation operation = generateOperation();
        switch (operation) {
            case ADDITION, SUBTRACTION, MULTIPLICATION -> {
                return new ExpressionTask(generateNumber(), operation, generateNumber());
            }
            case DIVISION -> {
                int operand = generateNonZero();
                return new ExpressionTask(operand * generateNumber(), operation, operand);
            }
        };
        throw new RuntimeException("Unknown operation");
    }
}
