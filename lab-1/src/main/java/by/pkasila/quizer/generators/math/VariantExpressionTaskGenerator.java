package by.pkasila.quizer.generators.math;

import by.pkasila.quizer.common.MathOperation;
import by.pkasila.quizer.exceptions.BadGeneratorException;
import by.pkasila.quizer.generators.VariantTaskGenerator;
import by.pkasila.quizer.tasks.math.VariantExpressionTask;

import java.util.EnumSet;

public class VariantExpressionTaskGenerator extends AbstractExpressionTaskGenerator<VariantExpressionTask> implements VariantTaskGenerator<VariantExpressionTask> {
    /**
     * @param minNumber    минимальное число
     * @param maxNumber    максимальное число
     * @param operationSet множество разрешённых операций
     */
    public VariantExpressionTaskGenerator(int minNumber, int maxNumber, EnumSet<MathOperation> operationSet) throws BadGeneratorException {
        super(minNumber, maxNumber, operationSet);
    }

    @Override
    public void validateGenerator() throws BadGeneratorException {
        super.validateGenerator();
        if (getDiffNumber() < 3) {
            throw new BadGeneratorException("Task will always have repeating variants");
        }
    }

    @Override
    public VariantExpressionTask generateUnvalidated() {
        return new VariantExpressionTask(
                getRandomNumber(),
                getRandomOperation(),
                getRandomNumber(),
                getRandomNumber(),
                getRandomNumber(),
                getRandomVariant());
    }
}
