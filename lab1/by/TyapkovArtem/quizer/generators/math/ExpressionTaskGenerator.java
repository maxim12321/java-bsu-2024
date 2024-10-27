package lab1.by.TyapkovArtem.quizer.generators.math;

import lab1.by.TyapkovArtem.quizer.Operation;
import lab1.by.TyapkovArtem.quizer.Task;
import lab1.by.TyapkovArtem.quizer.tasks.math.ExpressionTask;

public class ExpressionTaskGenerator extends AbstractMathTaskGenerator {
    public ExpressionTaskGenerator(Config config) {
        super(config);
    }

    @Override
    public Task generate() {
        Operation oper = GenNextOp_();
        int a = GenNextArg_();
        int b = GenNextArg_();
        if (oper.equals(Operation.Division)) {
            return new ExpressionTask(a * b, b, oper);
        }
        return new ExpressionTask(a, b, oper);
    }
}
