package by.SanchukS.quizer.generators.math;

import by.SanchukS.quizer.TaskGenerator;

public interface MathTaskGenerator extends TaskGenerator {
    public int getMinNumber(); // получить минимальное число
    public int getMaxNumber(); // получить максимальное число

    /**
     * @return разница между максимальным и минимальным возможным числом
     */
    default int getDiffNumber() {
        return getMaxNumber() - getMinNumber();
    }
}
