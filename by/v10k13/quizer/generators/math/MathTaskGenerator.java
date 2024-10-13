package by.v10k13.quizer.generators.math;

import by.v10k13.quizer.TaskGenerator;
import by.v10k13.quizer.tasks.math.MathTask;

public interface MathTaskGenerator<T extends MathTask> extends TaskGenerator<T> {
    double getMinimum();
    double getMaximum();

    default double getDiff() {
        return getMaximum() - getMinimum();
    }
}
