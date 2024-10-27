package by.request3301.quizer.generators.math;

import by.request3301.quizer.MathEngine;
import by.request3301.quizer.tasks.math.EquationMathTask;
import by.request3301.quizer.tasks.math.MathTask.Operation;

import java.util.EnumSet;

public class EquationMathTaskGenerator extends AbstractMathTaskGenerator<EquationMathTask> {
    public EquationMathTaskGenerator(int minNumber, int maxNumber, EnumSet<Operation> operations) {
        super(minNumber, maxNumber, operations);
    }

    @Override
    public EquationMathTask generate() {
        int number1 = random.nextInt(maxNumber - minNumber) + minNumber;
        int number2 = random.nextInt(maxNumber - minNumber) + minNumber;
        int x_position = random.nextInt(2);
        String operation = RandomOperator().getOperation();
        String lhs;
        if (x_position == 0) {
            lhs = number1 + operation + "x";
        } else {
            lhs = "x" + operation + number2;
        }
        String rhs = String.valueOf(MathEngine.eval(number1 + operation + number2));
        return new EquationMathTask(lhs, rhs);
    }
}
