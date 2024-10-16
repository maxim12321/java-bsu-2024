package by.KirillBukato.quizer.generators.math;

import by.KirillBukato.quizer.generators.VariantTaskGenerator;
import by.KirillBukato.quizer.tasks.math.ExpressionVariantTask;
import by.KirillBukato.quizer.tasks.math.MathTask;

import java.util.EnumSet;

public class ExpressionVariantTaskGenerator extends AbstractExpressionTaskGenerator<ExpressionVariantTask> implements VariantTaskGenerator<ExpressionVariantTask> {
    /**
     * @param minNumber минимальное число
     * @param maxNumber максимальное число
     * @param enumSet   множество разрешённых операций
     */
    public ExpressionVariantTaskGenerator(int minNumber, int maxNumber, EnumSet<MathTask.Operation> enumSet) {
        super(minNumber, maxNumber, enumSet);
    }

    /**
     * Расширенная валидация генератора.
     * Если доступно меньше трёх чисел для генерации, то будет несколько одинаковых вариантов ответа, мы такого не хотим
     */
    @Override
    public RuntimeException validateGenerator() {
        RuntimeException e = super.validateGenerator();
        if (e != null) return e;
        if (getDiffNumber() < 3) return new IllegalArgumentException("Task will always have repeating variants");
        return null;
    }

    @Override
    public ExpressionVariantTask generateUnvalidated() {
        return new ExpressionVariantTask(
                getRandomNumber(),
                getRandomOperation(),
                getRandomNumber(),
                getRandomNumber(),
                getRandomNumber(),
                getRandomVariant());
    }
}
