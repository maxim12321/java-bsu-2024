package by.KirillBukato.quizer.generators.math;

import by.KirillBukato.quizer.tasks.math.EquationTask;
import by.KirillBukato.quizer.tasks.math.MathTask;

import java.util.EnumSet;
import java.util.Random;

public class EquationTaskGenerator extends AbstractMathTaskGenerator<EquationTask> {

    /**
     * Расширенная валидация генератора.
     * Если генерируются примеры с числами на отрезке [0,0] и операцией деления, то они всегда будут невалидны.
     * Иначе есть шанс, что сгенерируется валидный.
     */
    @Override
    public RuntimeException validateGenerator() {
        if (operationsIsDivisionAndMultiplication() && getMinNumber() == 0 && getMaxNumber() == 0) {
            return new IllegalArgumentException("Task will always have zero division");
        } else return null;
    }

    /**
     * @param minNumber              минимальное число
     * @param maxNumber              максимальное число
     * @param enumSet                множество разрешённых операций
     */
    public EquationTaskGenerator(int minNumber, int maxNumber, EnumSet<MathTask.Operation> enumSet) {
        super(minNumber, maxNumber, enumSet);
    }

    @Override
    public EquationTask generateUnvalidated() {
        Random random = new Random();
        return new EquationTask(getRandomNumber(), getRandomOperation(), getRandomNumber(),
                random.nextInt(2) == 0);
    }
}
