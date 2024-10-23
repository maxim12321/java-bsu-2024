package by.MatsveiZorka.quizer.generators;

import by.MatsveiZorka.quizer.tasks.MathTask;
import by.MatsveiZorka.quizer.tasks.Operators;

import java.util.EnumSet;
import java.util.Random;

public abstract class AbstractMathTaskGenerator<T extends MathTask> implements MathTaskGenerator<T> {
    protected final int Max_;
    protected final int Min_;

    public AbstractMathTaskGenerator(int max, int min) {
        Max_ = max;
        Min_ = min;
    }

    @Override
    public int getMinNumber() {
        return Min_;
    }

    @Override
    public int getMaxNumber() {
        return Max_;
    }

    public int generateIntWithinRange(Random rand) {
        return rand.nextInt(getDiffNumber() + 1) + Min_;
    }

    public Operators generateOperator(Random rand, EnumSet<Operators> op) {
        return op.stream().skip(rand.nextInt(op.size()) - 1).findFirst().get();
    }
}
