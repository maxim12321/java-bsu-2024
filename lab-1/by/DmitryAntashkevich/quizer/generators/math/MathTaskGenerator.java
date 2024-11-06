package by.DmitryAntashkevich.quizer.generators.math;

import by.DmitryAntashkevich.quizer.TaskGenerator;
import by.DmitryAntashkevich.quizer.tasks.math.MathTask;

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
