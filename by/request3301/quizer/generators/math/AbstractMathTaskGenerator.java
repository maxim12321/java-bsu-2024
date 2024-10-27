package by.request3301.quizer.generators.math;

import by.request3301.quizer.exceptions.GeneratorExhaustedException;
import by.request3301.quizer.tasks.math.MathTask;
import by.request3301.quizer.tasks.math.MathTask.Operation;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Random;

public abstract class AbstractMathTaskGenerator<T extends MathTask> implements MathTaskGenerator<T> {
    Random random = new Random();
    int minNumber;
    int maxNumber;
    ArrayList<Operation> operations;

    public AbstractMathTaskGenerator(
            int minNumber,
            int maxNumber,
            EnumSet<Operation> operations
    ) {
        if (minNumber >= maxNumber) {
            throw new InvalidParameterException("minNumber must be less than maxNumber");
        }
        if (minNumber <= 0 && maxNumber > 0 && operations.contains(Operation.DIVISION)) {
            throw new InvalidParameterException("Division by 0 is possible");
        }
        this.minNumber = minNumber;
        this.maxNumber = maxNumber;
        this.operations = new ArrayList<>(operations);
    }

    public int getMinNumber() {
        return minNumber;
    }

    public int getMaxNumber() {
        return maxNumber;
    }

    Operation RandomOperator() {
        int operatorIndex = random.nextInt(operations.size());
        return operations.get(operatorIndex);
    }

    public abstract T generate() throws GeneratorExhaustedException;
}
