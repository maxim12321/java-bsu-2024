package by.AlexAgeev.quizer.generators.math;

import by.AlexAgeev.quizer.tasks.math.MathTask;

import java.util.Random;


public abstract class AbstractMathTaskGenerator implements MathTaskGenerator {

    public AbstractMathTaskGenerator(int minNumber, int maxNumber) {
        this.minNumber = minNumber;
        this.maxNumber = maxNumber;
    }

    @Override
    public int getMinNumber() {
        return minNumber;
    }

    @Override
    public int getMaxNumber() {
        return maxNumber;
    }

    protected final int minNumber;
    protected final int maxNumber;
}
