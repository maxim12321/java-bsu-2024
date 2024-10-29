package by.SanchukS.quizer.generators.math;

import by.SanchukS.quizer.TaskGenerator;
import by.SanchukS.quizer.tasks.math.MathTask;

public interface MathTaskGenerator extends TaskGenerator<MathTask> {
    public int getMinNumber(); // получить минимальное число
    public int getMaxNumber(); // получить максимальное число

    /**
     * @return разница между максимальным и минимальным возможным числом
     */
    default int getDiffNumber() {
        return getMaxNumber() - getMinNumber();
    }
}
