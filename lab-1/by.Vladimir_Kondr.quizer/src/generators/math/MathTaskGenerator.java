package generators.math;

import core.TaskGenerator;
import tasks.math.MathTask;

public interface MathTaskGenerator<T extends MathTask> extends TaskGenerator<T> {
    int getMinNumber();
    int getMaxNumber();
    default int getDiffNumber() {
        return getMaxNumber() - getMinNumber();
    }
    IllegalArgumentException isValid();
    T generate();
}
