package by.DmitryAntashkevich.quizer.generators.math;

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
        if (!isValid()) {
            throw new IllegalArgumentException("Invalid generator parameters");
        }
    }

    AbstractMathTaskGenerator(int minNumber, int maxNumber) {
        this(minNumber, maxNumber, EnumSet.allOf(Operation.class));
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
        int min = minNumber / divider;
        int max = maxNumber / divider;
        return random.nextInt(min, max + 1) * divider;
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
    protected final int tryCount = 100;
    private final Random random;
}
