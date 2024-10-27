package by.dilik14.quizer.generators.math;

import by.dilik14.quizer.tasks.math.MathTask;
import by.dilik14.quizer.tasks.math.MathTask.Operation;

import java.util.EnumSet;
import java.util.Random;

public abstract class AbstractMathTaskGenerator<T extends MathTask> implements MathTaskGenerator<T> {
    AbstractMathTaskGenerator(
            int minNumber,
            int maxNumber,
            EnumSet<Operation> allowedOperations) {
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
        if (allowedOperations.isEmpty())
            throw new IllegalArgumentException("No allowed operations");
        if (minNumber > maxNumber)
            throw new IllegalArgumentException("Min number is greater than max number");
    }

    protected int generateNumber() {
        return random.nextInt(minNumber, maxNumber);
    }

    protected int generateNonZero() {
        int number;
        do {
            number = random.nextInt(minNumber, maxNumber);
        } while (number == 0);

        return number;
    }

    protected Operation generateOperation() {
        return allowedOperations.stream().toList().get(random.nextInt(allowedOperations.size()));
    }

    protected boolean generateBool() {
        return random.nextBoolean();
    }

    public int getMinNumber() {
        return minNumber;
    }

    public int getMaxNumber() {
        return maxNumber;
    }

    protected final int minNumber;
    protected final int maxNumber;
    protected final EnumSet<Operation> allowedOperations;
    private final Random random;
}
