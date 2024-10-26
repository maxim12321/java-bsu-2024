package by.chewy_pegasus.quizer.generators.math;

import by.chewy_pegasus.quizer.tasks.math.MathTask;

import java.util.ArrayList;
import java.util.EnumSet;

public abstract class AbstractMathTaskGenerator<T extends MathTask> implements MathTaskGenerator<MathTask> {

    @Override
    public void validParameters(int min, int max, EnumSet<MathTask.Operation> operationEnumSet) throws IllegalArgumentException {
        if (!operationEnumSet.contains(MathTask.Operation.MULT) &&
                !operationEnumSet.contains(MathTask.Operation.DIV) &&
                !operationEnumSet.contains(MathTask.Operation.DIFF) &&
                !operationEnumSet.contains(MathTask.Operation.SUM)) {
            throw new IllegalArgumentException("ExpressionTaskGenerator: no possible operation");
        }
        if (min > max) {
            throw new IllegalArgumentException("ExpressionTaskGenerator: min > max");
        }
        if (!operationEnumSet.contains(MathTask.Operation.MULT) &&
                operationEnumSet.contains(MathTask.Operation.DIV) &&
                !operationEnumSet.contains(MathTask.Operation.DIFF) &&
                !operationEnumSet.contains(MathTask.Operation.SUM) &&
                max == min && max == 0) {
            throw new IllegalArgumentException("ExpressionTaskGenerator: no possible operands");
        }
    }

    protected final int minNumber;
    protected final int maxNumber;
    protected final ArrayList<Character> a;

    public AbstractMathTaskGenerator(
            int minNumber,
            int maxNumber
    ) {
        this.maxNumber = maxNumber;
        this.minNumber = minNumber;
        a = new ArrayList<>();
    }

    @Override
    public int getMinNumber() {
        return minNumber;
    }

    @Override
    public int getMaxNumber() {
        return maxNumber;
    }
}
