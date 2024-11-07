package by.pkasila.quizer.generators.math;

import by.pkasila.quizer.common.MathOperation;
import by.pkasila.quizer.exceptions.BadGeneratorException;
import by.pkasila.quizer.tasks.math.AbstractExpressionTask;

import java.util.EnumSet;

public abstract class AbstractExpressionTaskGenerator<T extends AbstractExpressionTask> extends AbstractMathTaskGenerator<T> {

    /**
     * @param minNumber    минимальное число
     * @param maxNumber    максимальное число
     * @param operationSet множество разрешённых операций
     */
    public AbstractExpressionTaskGenerator(int minNumber, int maxNumber, EnumSet<MathOperation> operationSet) throws BadGeneratorException {
        super(minNumber, maxNumber, operationSet);
    }

    @Override
    public void validateGenerator() throws BadGeneratorException {
        super.validateGenerator();
        if (hasDivision() && getMinNumber() == 0 && getMaxNumber() == 0) {
            throw new BadGeneratorException("always division by zero");
        }
    }
}
