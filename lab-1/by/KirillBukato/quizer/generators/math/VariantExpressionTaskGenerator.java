package by.KirillBukato.quizer.generators.math;

import by.KirillBukato.quizer.exceptions.InvalidGeneratorException;
import by.KirillBukato.quizer.generators.VariantTaskGenerator;
import by.KirillBukato.quizer.tasks.math.MathTask;
import by.KirillBukato.quizer.tasks.math.VariantExpressionTask;

import java.util.EnumSet;
import java.util.Optional;

public class VariantExpressionTaskGenerator extends AbstractExpressionTaskGenerator<VariantExpressionTask> implements VariantTaskGenerator<VariantExpressionTask> {
    /**
     * @param minNumber    минимальное число
     * @param maxNumber    максимальное число
     * @param operationSet множество разрешённых операций
     */
    public VariantExpressionTaskGenerator(int minNumber, int maxNumber, EnumSet<MathTask.Operation> operationSet) throws InvalidGeneratorException {
        super(minNumber, maxNumber, operationSet);
    }

    /**
     * Расширенная валидация генератора.
     * Если доступно меньше трёх чисел для генерации, то будет несколько одинаковых вариантов ответа, мы такого не хотим
     */
    @Override
    public Optional<InvalidGeneratorException> validateGenerator() {
        return super.validateGenerator().or(() -> {
            if (getDiffNumber() < 3)
                return Optional.of(new InvalidGeneratorException("Task will always have repeating variants"));
            return Optional.empty();
        });
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
