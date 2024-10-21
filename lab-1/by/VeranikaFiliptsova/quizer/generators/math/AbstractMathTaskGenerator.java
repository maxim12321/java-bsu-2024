package by.VeranikaFiliptsova.quizer.generators.math;

import by.VeranikaFiliptsova.quizer.Task;

abstract public class AbstractMathTaskGenerator implements MathTaskGenerator{
    int minNumb;
    int maxNumb;

    @Override
    public int getMinNumber() {
        return minNumb;
    }

    @Override
    public int getMaxNumber() {
        return maxNumb;
    }

    @Override
    abstract public Task generate();

    public int getDiffNumber() {
        return maxNumb - minNumb;
    }
}
