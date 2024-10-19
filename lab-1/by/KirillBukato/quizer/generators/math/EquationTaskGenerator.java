package by.KirillBukato.quizer.generators.math;

import by.KirillBukato.quizer.exceptions.InvalidGeneratorException;
import by.KirillBukato.quizer.tasks.math.EquationTask;
import by.KirillBukato.quizer.tasks.math.MathOperation;

import java.util.EnumSet;
import java.util.Random;

public class EquationTaskGenerator extends AbstractMathTaskGenerator<EquationTask> {

    /**
     * @param minNumber    минимальное число
     * @param maxNumber    максимальное число
     * @param operationSet множество разрешённых операций
     */
    public EquationTaskGenerator(int minNumber, int maxNumber, EnumSet<MathOperation> operationSet) throws InvalidGeneratorException {
        super(minNumber, maxNumber, operationSet);
    }

    /**
     * Расширенная валидация генератора.
     * Если генерируются примеры с числами на отрезке [0,0] и операцией деления, то они всегда будут невалидны.
     * Иначе есть шанс, что сгенерируется валидный.
     */
    @Override
    public void validateGenerator() throws InvalidGeneratorException {
        super.validateGenerator();
        if (operationsIsDivisionAndMultiplication() && getMinNumber() == 0 && getMaxNumber() == 0) {
            throw new InvalidGeneratorException("Task will always have zero division");
        }
    }

    @Override
    public EquationTask generateUnvalidated() {
        Random random = new Random();
        return new EquationTask(getRandomNumber(), getRandomOperation(), getRandomNumber(),
                random.nextInt(2) == 0);
    }
}
