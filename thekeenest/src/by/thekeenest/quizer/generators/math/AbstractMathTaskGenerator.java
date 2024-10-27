package by.thekeenest.quizer.generators.math;

import by.thekeenest.quizer.tasks.math.MathTask;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Random;

public abstract class AbstractMathTaskGenerator<T extends MathTask> implements MathTaskGenerator<T> {
    protected final int maxNumber;
    protected final int minNumber;
    protected final EnumSet<MathTask.Operation> operations;
    protected final Random random;

    protected AbstractMathTaskGenerator(int minNumber, int maxNumber,
                                        EnumSet<MathTask.Operation> operations) {
        if (minNumber >= maxNumber) {
            throw new IllegalArgumentException("minNumber must be less than maxNumber");
        }
        if (operations == null || operations.isEmpty()) {
            throw new IllegalArgumentException("operations set cannot be null or empty");
        }

        this.minNumber = minNumber;
        this.maxNumber = maxNumber;
        this.operations = operations;
        this.random = new Random();
    }

    @Override
    public int getMinNumber() {
        return minNumber;
    }

    @Override
    public int getMaxNumber() {
        return maxNumber;
    }

    protected int generateNumber() {
        return random.nextInt(maxNumber - minNumber + 1) + minNumber;
    }

    protected MathTask.Operation getRandomOperation() {
        List<MathTask.Operation> operationList = new ArrayList<>(operations);
        return operationList.get(random.nextInt(operationList.size()));
    }
}
