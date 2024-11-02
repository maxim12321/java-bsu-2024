package by.SanchukS.quizer.generators.math;

import by.SanchukS.quizer.Expression;
import by.SanchukS.quizer.Operation;
import by.SanchukS.quizer.exceptions.NullArgumentException;
import by.SanchukS.quizer.generators.ExpressionGenerator;
import by.SanchukS.quizer.tasks.math.MathTask;

import java.util.EnumSet;

public abstract class AbstractMathTaskGenerator implements MathTaskGenerator {
    private final ExpressionGenerator expressionGenerator;

    /**
     * @param minNumber              минимальное число
     * @param maxNumber              максимальное число
     * @param operations             набор разрешённых операций
     */
    protected AbstractMathTaskGenerator(
            int minNumber,
            int maxNumber,
            EnumSet<Operation> operations
    ) {
        if (minNumber > maxNumber) throw new IllegalArgumentException("minNumber > maxNumber");
        if (operations == null) throw new NullArgumentException("operations");
        this.expressionGenerator = new ExpressionGenerator(minNumber, maxNumber, operations);
    }

    @Override
    public abstract MathTask generate();

    protected Expression generateExpression() {
        return expressionGenerator.generate();
    }

    @Override
    public int getMaxNumber() {
        return expressionGenerator.getMaxNumber();
    }

    @Override
    public int getMinNumber() {
        return expressionGenerator.getMinNumber();
    }
}
