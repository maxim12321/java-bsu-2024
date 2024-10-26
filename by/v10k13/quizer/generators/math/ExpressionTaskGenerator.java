package by.v10k13.quizer.generators.math;

import by.v10k13.quizer.tasks.math.ExpressionTask;

public class ExpressionTaskGenerator extends AbstractMathTaskGenerator<ExpressionTask> {
    public ExpressionTaskGenerator(MathTaskGeneratorConfig config) {
        super(config);
    }

    @Override
    public ExpressionTask generate() {
        return new ExpressionTask(GetNextRoundedRangedNumber_(), GetNextRoundedRangedNumber_(), GetNextOperator_());
    }
}
