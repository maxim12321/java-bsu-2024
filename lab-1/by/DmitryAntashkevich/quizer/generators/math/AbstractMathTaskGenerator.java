package by.DmitryAntashkevich.quizer.generators.math;

import by.DmitryAntashkevich.quizer.exceptions.InvalidTaskException;
import by.DmitryAntashkevich.quizer.tasks.math.MathTask.Operation;

import java.util.EnumSet;
import java.util.Random;

public abstract class AbstractMathTaskGenerator implements MathTaskGenerator {
    AbstractMathTaskGenerator(
            int minNumber,
            int maxNumber,
            EnumSet<Operation> allowedOperations
    ) {
        this.minNumber = minNumber;
        this.maxNumber = maxNumber;
        this.allowedOperations = allowedOperations;
        this.random = new Random();
        validate();
    }

    AbstractMathTaskGenerator(int minNumber, int maxNumber) {
        this(minNumber, maxNumber, EnumSet.allOf(Operation.class));
    }

    private void validate() {
        if (allowedOperations.isEmpty()) throw new InvalidTaskException("No allowed operations");
        if (minNumber > maxNumber) throw new InvalidTaskException("Min number is greater than max number");
        if (minNumber == 0 && maxNumber == 0) throw new InvalidTaskException("Both min and max numbers equal 0");
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
        return random.nextInt(minNumber, maxNumber + 1);
    }

    protected int generateNonZeroNumber() {
        if (maxNumber < 0 || minNumber > 0) {
            return generateNumber();
        }
        int result = random.nextInt(minNumber, maxNumber);
        if (result >= 0) {
            result++;
        }
        return result;
    }

    protected int generateMultiple(int divider) {
        divider = Math.abs(divider);
        int min = minNumber / divider;
        int max = maxNumber / divider;
        return random.nextInt(min, max + 1) * divider;
    }

    protected int generateNonZeroMultiple(int divider) {
        divider = Math.abs(divider);
        int min = minNumber / divider;
        int max = maxNumber / divider;
        if (max < 0 || min > 0) {
            return generateMultiple(divider);
        }
        int result = random.nextInt(min, max);
        if (result >= 0) {
            result++;
        }
        return result * divider;
    }

    protected Operation generateOperation() {
        return allowedOperations.stream().toList().get(random.nextInt(allowedOperations.size()));
    }

    protected boolean generateBool() {
        return random.nextInt(2) == 1;
    }

    protected final int minNumber;
    protected final int maxNumber;
    protected final EnumSet<Operation> allowedOperations;
    private final Random random;
}
