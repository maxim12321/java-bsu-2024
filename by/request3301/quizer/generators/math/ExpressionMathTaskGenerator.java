package by.request3301.quizer.generators.math;

import by.request3301.quizer.tasks.math.ExpressionMathTask;
import by.request3301.quizer.tasks.math.MathTask.Operation;

import java.util.EnumSet;

public class ExpressionMathTaskGenerator extends AbstractMathTaskGenerator<ExpressionMathTask> {
    public ExpressionMathTaskGenerator(int minNumber, int maxNumber, EnumSet<Operation> operations) {
        super(minNumber, maxNumber, operations);
    }

    @Override
    public ExpressionMathTask generate() {
        int number1 = random.nextInt(maxNumber - minNumber) + minNumber;
        int number2 = random.nextInt(maxNumber - minNumber) + minNumber;
        String operation = RandomOperator().getOperation();
        String lhs = number1 + operation + number2;
        String rhs = "?";
        return new ExpressionMathTask(lhs, rhs);
    }
}
