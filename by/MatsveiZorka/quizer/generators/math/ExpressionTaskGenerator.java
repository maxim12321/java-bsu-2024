package by.MatsveiZorka.quizer.generators.math;

import by.MatsveiZorka.quizer.tasks.math.ExpressionMathTask;
import by.MatsveiZorka.quizer.tasks.Operators;

import java.util.EnumSet;
import java.util.Random;

public class ExpressionTaskGenerator extends AbstractMathTaskGenerator<ExpressionMathTask> {

    final Random Random_;
    final EnumSet<Operators> Ops_;

    public ExpressionTaskGenerator(int max, int min, EnumSet<Operators> ops) {
        super(max, min);
        if (ops == null || ops.isEmpty())
            throw new NullPointerException("Null or empty operators.");
        Ops_ = ops;
        Random_ = new Random();
    }

    @Override
    public ExpressionMathTask generate() {
        int a = generateIntWithinRange(Random_);
        int b = generateIntWithinRange(Random_);
        var op = generateOperator(Random_, Ops_);

        return new ExpressionMathTask(a, b, op);
    }
}
