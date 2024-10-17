package by.KirillBukato.quizer.generators.math;

import by.KirillBukato.quizer.exceptions.InvalidGeneratorException;
import by.KirillBukato.quizer.tasks.math.AbstractExpressionTask;
import by.KirillBukato.quizer.tasks.math.MathTask;

import java.util.EnumSet;

public abstract class AbstractExpressionTaskGenerator<T extends AbstractExpressionTask> extends AbstractMathTaskGenerator<T> {

    /**
     * @param minNumber    минимальное число
     * @param maxNumber    максимальное число
     * @param operationSet множество разрешённых операций
     */
    public AbstractExpressionTaskGenerator(int minNumber, int maxNumber, EnumSet<MathTask.Operation> operationSet) {
        super(minNumber, maxNumber, operationSet);
    }

    /**
     * Расширенная валидация генератора.
     * Если генерируются примеры с числами на отрезке [0,0] и операцией деления, то они всегда будут невалидны.
     * Иначе есть шанс, что сгенерируется валидный.
     */
    @Override
    public InvalidGeneratorException validateGenerator() {
        InvalidGeneratorException exception = super.validateGenerator();
        if (exception != null) {
            return exception;
        }
        if (operationsIsDivision() && getMinNumber() == 0 && getMaxNumber() == 0) {
            return new InvalidGeneratorException("Task will always have zero division");
        } else return null;
    }
}
