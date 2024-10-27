package by.request3301.quizer.generators.math;

import by.request3301.quizer.TaskGenerator;
import by.request3301.quizer.tasks.math.MathTask;

public interface MathTaskGenerator<T extends MathTask> extends TaskGenerator<T> {
    int getMinNumber();

    int getMaxNumber();

    default int getDiffNumber() {
        return getMaxNumber() - getMinNumber();
    }
}
