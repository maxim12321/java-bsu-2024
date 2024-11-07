package by.volkovets.quizer.generators.math;

import by.volkovets.quizer.TaskGenerator;
import by.volkovets.quizer.tasks.math.MathTask;

public interface MathTaskGenerator<T extends MathTask> extends TaskGenerator<T> {
    int getMinNumber(); // получить минимальное число

    int getMaxNumber(); // получить максимальное число

    default int getDiffNumber() {
        return getMaxNumber() - getMinNumber();
    }
}
