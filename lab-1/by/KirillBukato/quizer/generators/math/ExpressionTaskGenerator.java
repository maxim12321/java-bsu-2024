package by.KirillBukato.quizer.generators.math;

import by.KirillBukato.quizer.generators.VariantTaskGenerator;
import by.KirillBukato.quizer.tasks.math.ExpressionTask;
import by.KirillBukato.quizer.tasks.math.MathTask;

import java.util.*;


public class ExpressionTaskGenerator extends AbstractMathTaskGenerator implements VariantTaskGenerator {

    @Override
    public RuntimeException validateGenerator() {
        RuntimeException exception = super.validateGenerator();
        if (exception != null) {
            return exception;
        }
        if (operationsIsDivision() && getMinNumber() == 0 && getMaxNumber() == 0) {
            return new IllegalArgumentException("Task will always have zero division");
        } else return null;
    }

    /**
     * @param minNumber              минимальное число
     * @param maxNumber              максимальное число
     * @param enumSet                множество разрешённых операций
     */
    public ExpressionTaskGenerator(int minNumber, int maxNumber, EnumSet<MathTask.Operation> enumSet) {
        super(minNumber, maxNumber, enumSet);
    }

    /**
     * return задание типа {@link ExpressionTask}
     */
    @Override
    public ExpressionTask generate() {
        ExpressionTask task;
        do {
            task = new ExpressionTask(getRandomNumber(), getRandomOperation(), getRandomNumber());
        } while (!task.isValid());
        return task;
    }
}
