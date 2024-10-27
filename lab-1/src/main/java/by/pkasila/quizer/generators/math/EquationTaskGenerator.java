package by.pkasila.quizer.generators.math;

import by.pkasila.quizer.common.MathOperation;
import by.pkasila.quizer.exceptions.BadGeneratorException;
import by.pkasila.quizer.tasks.math.EquationTask;

import java.util.EnumSet;
import java.util.Random;

public class EquationTaskGenerator extends AbstractMathTaskGenerator<EquationTask> {

    /**
     * @param minNumber    минимальное число
     * @param maxNumber    максимальное число
     * @param operationSet множество разрешённых операций
     */
    public EquationTaskGenerator(int minNumber, int maxNumber, EnumSet<MathOperation> operationSet) throws BadGeneratorException {
        super(minNumber, maxNumber, operationSet);
    }

    @Override
    public void validateGenerator() throws BadGeneratorException {
        super.validateGenerator();
        if (hasDivisionAndMultiplication() && getMinNumber() == 0 && getMaxNumber() == 0) {
            throw new BadGeneratorException("Always division by zero");
        }
    }

    @Override
    public EquationTask generateUnvalidated() {
        Random random = new Random();
        return new EquationTask(getRandomNumber(), getRandomOperation(), getRandomNumber(),
                random.nextInt(2) == 0);
    }
}
