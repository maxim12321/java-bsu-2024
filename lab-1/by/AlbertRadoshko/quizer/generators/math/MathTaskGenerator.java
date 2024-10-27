package by.AlbertRadoshko.quizer.generators.math;

import by.AlbertRadoshko.quizer.TaskGenerator;
import by.AlbertRadoshko.quizer.tasks.math.MathTask;

public interface MathTaskGenerator<T extends MathTask> extends TaskGenerator<T> {
    int getMinNumber();
    int getMaxNumber();

    /**
     * @return разница между максимальным и минимальным возможным числом
     */
    default int getDiffNumber() {
        return getMaxNumber() - getMinNumber();
    }
}
