package KlimovMikh.quizer.generators.math;

import KlimovMikh.quizer.tasks.math.ExpressionTask;
import KlimovMikh.quizer.tasks.math.MathTask;

import java.util.EnumSet;

public class ExpressionTaskGenerator extends AbstractMathTaskGenerator<ExpressionTask> {
    public ExpressionTaskGenerator(
            int minNumber,
            int maxNumber,
            EnumSet<MathTask.Operation> operations
    ) {
        super(minNumber, maxNumber, operations);
    }

    public ExpressionTask generate() {
        generateTask();
        return new ExpressionTask(number1, number2, operation);
    }
}
