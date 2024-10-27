package by.AlbertRadoshko.quizer.generators.math;

import by.AlbertRadoshko.quizer.tasks.math.MathTask;

import java.util.EnumSet;
import java.util.Random;

public abstract class AbstractMathTaskGenerator<T extends MathTask> implements MathTaskGenerator<T> {
    AbstractMathTaskGenerator(
            int minNumber,
            int maxNumber,
            EnumSet<MathTask.Operation> allowed
    ) {
        this.minNumber = minNumber;
        this.maxNumber = maxNumber;
        this.allowed = allowed;
        rand = new Random();
    }

    @Override
    public int getMinNumber() {
        return minNumber;
    }

    @Override
    public int getMaxNumber() {
        return maxNumber;
    }

    public int genMultiple(int x) {
        if ((minNumber + x - 1) / x >= maxNumber / x + 1) {
            return x;
        }
        return rand.nextInt((minNumber + x - 1) / x, maxNumber / x + 1) * x;
    }

    public int genNonZero() {
        if (minNumber <= 0 && 0 <= maxNumber) {
            if (rand.nextInt(2) == 0) {
                return rand.nextInt(minNumber, 0);
            }
            return rand.nextInt(1, maxNumber + 1);
        }
        return rand.nextInt(minNumber, maxNumber + 1);
    }

    int minNumber;
    int maxNumber;
    EnumSet<MathTask.Operation> allowed;
    Random rand;
}
