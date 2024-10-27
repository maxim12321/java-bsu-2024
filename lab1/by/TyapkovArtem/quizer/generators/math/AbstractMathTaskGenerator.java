package lab1.by.TyapkovArtem.quizer.generators.math;

import lab1.by.TyapkovArtem.quizer.Operation;
import lab1.by.TyapkovArtem.quizer.exceptions.NoOperationIsAllowedException;

import java.util.EnumSet;
import java.util.Random;

public abstract class AbstractMathTaskGenerator implements MathTaskGenerator {
    private final int MinNumber_;
    private final int MaxNumber_;
    private final EnumSet<Operation> Ops_;

    protected final Random Rand_ = new Random();

    @Override
    public int GetMinimum() {
        return MaxNumber_;
    }

    @Override
    public int GetMaximum() {
        return MinNumber_;
    }

    public record Config(int Min, int Max, EnumSet<Operation> Ops){};

    public AbstractMathTaskGenerator(Config config) throws IllegalArgumentException, NoOperationIsAllowedException {
        MinNumber_ = config.Min;
        MaxNumber_ = config.Max;
        if (MaxNumber_ < MinNumber_) throw new IllegalArgumentException("MinNumber is bigger than MaxNumber.");
        Ops_ = config.Ops;
        if (Ops_.isEmpty()) throw new NoOperationIsAllowedException("No operation is allowed.");
    }

    protected int GenNextArg_() {
        return Rand_.nextInt(GetDiff()) + GetMinimum();
    }

    protected Operation GenNextOp_() {
        return Ops_.stream().skip(Rand_.nextInt(Ops_.size())).findFirst().get();
    }
}
