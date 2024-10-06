package by.KirillBukato.quizer.generators.math;

import by.KirillBukato.quizer.generators.VariantTaskGenerator;
import by.KirillBukato.quizer.tasks.VariantTask;
import by.KirillBukato.quizer.tasks.math.ExpressionVariantTask;
import by.KirillBukato.quizer.tasks.math.MathTask;

import java.util.EnumSet;
import java.util.Random;

public class ExpressionVariantTaskGenerator extends ExpressionTaskGenerator implements VariantTaskGenerator {
    /**
     * @param minNumber минимальное число
     * @param maxNumber максимальное число
     * @param enumSet   множество разрешённых операций
     */
    public ExpressionVariantTaskGenerator(int minNumber, int maxNumber, EnumSet<MathTask.Operation> enumSet) {
        super(minNumber, maxNumber, enumSet);
    }

    @Override
    public RuntimeException validateGenerator() {
        RuntimeException e = super.validateGenerator();
        if (e != null) return e;
        if (getDiffNumber() < 3) return new IllegalArgumentException("Task will always have repeating variants");
        return null;
    }

    @Override
    public ExpressionVariantTask generate() {
        ExpressionVariantTask task;
        Random random = new Random();
        do {
            task = new ExpressionVariantTask(
                    getRandomNumber(),
                    getRandomOperation(),
                    getRandomNumber(),
                    getRandomNumber(),
                    getRandomNumber(),
                    switch (random.nextInt(3)) {
                        case 0 -> VariantTask.Variants.A;
                        case 1 -> VariantTask.Variants.B;
                        default -> VariantTask.Variants.C;
                    });
        } while (!task.isValid());
        return task;
    }
}
