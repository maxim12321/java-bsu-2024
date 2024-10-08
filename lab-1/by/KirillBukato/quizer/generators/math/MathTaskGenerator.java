package by.KirillBukato.quizer.generators.math;

import by.KirillBukato.quizer.TaskGenerator;
import by.KirillBukato.quizer.tasks.math.MathTask;

public interface MathTaskGenerator<T extends MathTask> extends TaskGenerator<T> {
    
    int getMinNumber();
    
    int getMaxNumber();

    RuntimeException validateGenerator();

    T generateUnvalidated();

    /**
     * @return разница между максимальным и минимальным возможным числом
     */
    default int getDiffNumber() {
        return getMaxNumber() - getMinNumber();
    }
}
