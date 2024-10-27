package by.dilik14.quizer.generators.math;

import by.dilik14.quizer.generators.TaskGenerator;
import by.dilik14.quizer.tasks.math.MathTask;

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