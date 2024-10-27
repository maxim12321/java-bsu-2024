package by.thekeenest.quizer.generators.math;

import by.thekeenest.quizer.generators.TaskGenerator;
import by.thekeenest.quizer.tasks.math.MathTask;

public interface MathTaskGenerator<T extends MathTask> extends TaskGenerator<T> {
    int getMinNumber();
    int getMaxNumber();

    default int getDiffNumber() {
        return getMaxNumber() - getMinNumber();
    }
}
