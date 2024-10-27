package by.v10k13.quizer.generators.math;

import by.v10k13.quizer.tasks.math.EquationTask;

public class EquationTaskGenerator extends AbstractMathTaskGenerator<EquationTask> {
    public EquationTaskGenerator(MathTaskGeneratorConfig config) {
        super(config);
    }

    @Override
    public EquationTask generate() {
        return new EquationTask(Random_.nextBoolean(), GetNextRoundedRangedNumber_(), GetNextRoundedRangedNumber_(), GetNextOperator_());
    }
}
