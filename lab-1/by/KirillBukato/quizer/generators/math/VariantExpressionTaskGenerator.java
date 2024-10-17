package by.KirillBukato.quizer.generators.math;

import by.KirillBukato.quizer.exceptions.InvalidGeneratorException;
import by.KirillBukato.quizer.generators.VariantTaskGenerator;
import by.KirillBukato.quizer.tasks.math.VariantExpressionTask;
import by.KirillBukato.quizer.tasks.math.MathTask;

import java.util.EnumSet;

public class VariantExpressionTaskGenerator extends AbstractExpressionTaskGenerator<VariantExpressionTask> implements VariantTaskGenerator<VariantExpressionTask> {
    /**
     * @param minNumber минимальное число
     * @param maxNumber максимальное число
     * @param operationSet   множество разрешённых операций
     */
    public VariantExpressionTaskGenerator(int minNumber, int maxNumber, EnumSet<MathTask.Operation> operationSet) {
        super(minNumber, maxNumber, operationSet);
    }

    /**
     * Расширенная валидация генератора.
     * Если доступно меньше трёх чисел для генерации, то будет несколько одинаковых вариантов ответа, мы такого не хотим
     */
    @Override
    public InvalidGeneratorException validateGenerator() {
        InvalidGeneratorException e = super.validateGenerator();
        if (e != null) return e;
        if (getDiffNumber() < 3) return new InvalidGeneratorException("Task will always have repeating variants");
        return null;
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
