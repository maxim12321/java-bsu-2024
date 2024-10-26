package KlimovMikh.quizer.generators.math;

import KlimovMikh.quizer.Task;
import KlimovMikh.quizer.TaskGenerator;

public interface MathTaskGenerator <T extends Task> extends TaskGenerator<T> {
    int getMinNumber(); // получить минимальное число
    int getMaxNumber(); // получить максимальное число

    /**
     * @return разница между максимальным и минимальным возможным числом
     */
    default int getDiffNumber() {
        return getMaxNumber() - getMinNumber();
    }
}
