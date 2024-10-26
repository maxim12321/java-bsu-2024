package by.kirsiv40.quizer.Generators.Math;

import by.kirsiv40.quizer.Generators.Math.Exceptions.BadNumericRangeException;
import by.kirsiv40.quizer.Tasks.Math.MathTask;
import java.util.concurrent.ThreadLocalRandom;

public abstract class AbstractMathTaskGenerator<T extends MathTask> implements MathTaskGenerator<T> {
    protected int minNumber = 0;
    protected int maxNumber = 0;

    @Override
    public int getMinNumber() {
        return minNumber;
    }

    @Override
    public int getMaxNumber() {
        return maxNumber;
    }

    protected int generateNumber() throws BadNumericRangeException {
        return ThreadLocalRandom.current().nextInt(minNumber, maxNumber);
    }

    protected int generateNonZero() throws BadNumericRangeException {
        if (minNumber == 0 && maxNumber == 1) {
            throw new BadNumericRangeException("cant create test because of only zero can be used in division operation");
        }
        int number = generateNumber();
        while (number == 0) {
            number = generateNumber();
        }
        return number;
    }

    public abstract T generate();
}
