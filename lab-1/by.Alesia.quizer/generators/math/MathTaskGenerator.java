package by.Alesia.quizer.generators.math;

import by.Alesia.quizer.TaskGenerator;
import by.Alesia.quizer.tasks.math.MathTask;

public interface MathTaskGenerator<T extends MathTask> extends TaskGenerator {
    int minNumber();

    int maxNumber();

    default int getDiffNumber() {
        return maxNumber() - minNumber();
    }

    T generate();
}