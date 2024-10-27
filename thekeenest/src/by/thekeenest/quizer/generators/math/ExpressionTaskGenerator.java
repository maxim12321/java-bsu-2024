package by.thekeenest.quizer.generators.math;

import by.thekeenest.quizer.tasks.math.ExpressionMathTask;
import by.thekeenest.quizer.tasks.math.MathTask;

import java.util.EnumSet;


public class ExpressionTaskGenerator extends AbstractMathTaskGenerator<ExpressionMathTask> {
    public ExpressionTaskGenerator(int minNumber, int maxNumber,
                                   EnumSet<MathTask.Operation> operations) {
        super(minNumber, maxNumber, operations);
    }

    @Override
    public ExpressionMathTask generate() {
        int first = generateNumber();
        int second = generateNumber();
        MathTask.Operation operation = getRandomOperation();
        return new ExpressionMathTask(first, second, operation);
    }
}
