package by.maxsavitsky.quizer.generators.math;

import by.maxsavitsky.quizer.generators.TaskGenerator;
import by.maxsavitsky.quizer.tasks.math.MathTask;

public interface MathTaskGenerator<T extends MathTask> extends TaskGenerator<T> {
    int getMinNumber(); // получить минимальное число

    int getMaxNumber(); // получить максимальное число
}
