package by.thekeenest.quizer.generators.math;

import by.thekeenest.quizer.tasks.math.EquationMathTask;
import by.thekeenest.quizer.tasks.math.MathTask;
import java.util.EnumSet;

public class EquationTaskGenerator extends AbstractMathTaskGenerator<EquationMathTask> {
    public EquationTaskGenerator(int minNumber, int maxNumber,
                                 EnumSet<MathTask.Operation> operations) {
        super(minNumber, maxNumber, operations);
    }

    @Override
    public EquationMathTask generate() {
        int known = generateNumber();
        int result = generateNumber();
        MathTask.Operation operation = getRandomOperation();
        boolean isLeftUnknown = random.nextBoolean();

        return new EquationMathTask(known, result, operation, isLeftUnknown);
    }
}