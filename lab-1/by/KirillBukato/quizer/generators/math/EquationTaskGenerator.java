package by.KirillBukato.quizer.generators.math;

import by.KirillBukato.quizer.tasks.math.EquationTask;
import by.KirillBukato.quizer.tasks.math.MathTask;

import java.util.EnumSet;
import java.util.Random;

public class EquationTaskGenerator extends AbstractMathTaskGenerator {

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

    /**
     * return задание типа {@link EquationTask}
     */
    @Override
    public EquationTask generate() {
        Random random = new Random();
        EquationTask task;
        do {
            task = new EquationTask(getRandomNumber(), getRandomOperation(), getRandomNumber(),
                    random.nextInt(2) == 0);
        } while (!task.isValid());
        return task;
    }
}
