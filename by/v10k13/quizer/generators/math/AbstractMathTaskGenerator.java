package by.v10k13.quizer.generators.math;

import by.v10k13.quizer.tasks.math.MathTask;

import java.util.EnumSet;
import java.util.Random;

public abstract class AbstractMathTaskGenerator<T extends MathTask> implements MathTaskGenerator<T> {
    private final double Min_;
    private final double Max_;
    protected final Random Random_ = new Random();
    private final EnumSet<MathTask.Operators> Ops_;

    public record MathTaskGeneratorConfig(int Min, int Max, EnumSet<MathTask.Operators> Ops){}

    public AbstractMathTaskGenerator(int min, int max, EnumSet<MathTask.Operators> ops) {
        Min_ = min;
        Max_ = max;
        Ops_ = ops;
    }

    public AbstractMathTaskGenerator(MathTaskGeneratorConfig config) {
        Min_ = config.Min;
        Max_ = config.Max;
        Ops_ = config.Ops;
    }

    @Override
    public double getMinimum() {
        return Min_;
    }

    @Override
    public double getMaximum() {
        return Max_;
    }

    protected MathTask.Operators GetNextOperator_() {
        int operator = Math.abs(Random_.nextInt()) % Ops_.size();
        return Ops_.stream().skip(operator).findFirst().get(); // Absolutely always correct! FCK WARNINGS
    }

    protected double GetNextRoundedRangedNumber_() {
        double r_bound = getDiff();
        return (double) Math.round(Random_.nextDouble(r_bound) + getMinimum());
    }
}
