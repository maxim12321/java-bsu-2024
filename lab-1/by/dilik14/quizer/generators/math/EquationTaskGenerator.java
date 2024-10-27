package by.dilik14.quizer.generators.math;

import java.util.EnumSet;

import by.dilik14.quizer.tasks.math.EquationTask;
import by.dilik14.quizer.tasks.math.MathTask.Operation;

public class EquationTaskGenerator extends AbstractMathTaskGenerator<EquationTask> {
    /**
     * @param minNumber         минимальное число
     * @param maxNumber         максимальное число
     * @param allowedOperations разрешенные операции (+, -, *, /)
     */
    public EquationTaskGenerator(
            int minNumber,
            int maxNumber,
            EnumSet<Operation> allowedOperations) {
        super(minNumber, maxNumber, allowedOperations);
    }

    /**
     * return задание типа {@link EquationTask}
     */
    public EquationTask generate() {
        Operation operation = generateOperation();
        boolean XFirst = generateBool();
        switch (operation) {
            case ADDITION, SUBTRACTION -> {
                return new EquationTask(generateNumber(), operation, generateNumber(), XFirst);
            }
            case MULTIPLICATION -> {
                int operand = generateNonZero();
                return new EquationTask(operand, operation, operand * generateNumber(), XFirst);
            }
            case DIVISION -> {
                if (XFirst){
                    return new EquationTask(generateNonZero(), operation, generateNumber(), XFirst);
                } else {
                    int operand = generateNonZero();
                    return new EquationTask(operand * generateNonZero(), operation, operand, XFirst);
                }
                
            }
        };
        throw new RuntimeException("Unknown operation");
    }
}
