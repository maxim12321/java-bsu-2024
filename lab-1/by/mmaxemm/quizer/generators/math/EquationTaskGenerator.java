package by.mmaxemm.quizer.generators.math;

import by.mmaxemm.quizer.tasks.math.EquationTask;
import by.mmaxemm.quizer.tasks.math.MathTask;
import java.util.EnumSet;

public class EquationTaskGenerator extends AbstractMathTaskGenerator {

    public EquationTaskGenerator(
            int minNumber,
            int maxNumber,
            EnumSet<MathTask.Operation> availableOperators
    ) {
        super(minNumber, maxNumber, availableOperators);
    }

    @Override
    public EquationTask generate() {
        int num1 = random.nextInt(maxNumber - minNumber + 1) + minNumber;
        int num2 = random.nextInt(maxNumber - minNumber + 1) + minNumber;
        int operatorIndex = random.nextInt(availableOperators.size());
        MathTask.Operation operator = availableOperators.get(operatorIndex);
        return new EquationTask(num1, num2, operator);
    }
}