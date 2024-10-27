package by.mmaxemm.quizer.generators.math;

import by.mmaxemm.quizer.tasks.math.MathTask;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Random;

public abstract class AbstractMathTaskGenerator implements MathTaskGenerator {
    protected final int minNumber;
    protected final int maxNumber;
    protected Random random;
    protected final ArrayList<MathTask.Operation> availableOperators;

    public AbstractMathTaskGenerator(int minNumber,
                              int maxNumber,
                              EnumSet<MathTask.Operation> availableOperators) {
        if(minNumber > maxNumber) {
            throw new IllegalArgumentException("min number cannot be greater than max number");
        }
        if(availableOperators.isEmpty()) {
            throw new IllegalArgumentException("At least one operator should be avaliable");
        }

        this.minNumber = minNumber;
        this.maxNumber = maxNumber;
        this.availableOperators = new ArrayList<>(availableOperators);
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
}
