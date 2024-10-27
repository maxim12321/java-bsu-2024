package by.TimurTahil.quizer.generators.math;

import by.TimurTahil.quizer.Task;


public abstract class AbstractMathTaskGenerator implements MathTaskGenerator {

    protected final int minNumber;
    protected final int maxNumber;

    public AbstractMathTaskGenerator(int minNumber, int maxNumber) {
        if (minNumber >= maxNumber) {
            throw new IllegalArgumentException("minNumber is bigger than maxNumber");
        }
        this.minNumber = minNumber;
        this.maxNumber = maxNumber;
    }

    public abstract Task generate();

    public int getMinNumber() {
        return minNumber;
    }

    public int getMaxNumber() {
        return maxNumber;
    }
}