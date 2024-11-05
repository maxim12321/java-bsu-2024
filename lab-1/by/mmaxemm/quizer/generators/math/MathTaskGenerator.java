package by.mmaxemm.quizer.generators.math;

import by.mmaxemm.quizer.TaskGenerator;

public interface MathTaskGenerator extends TaskGenerator {

    public int getMinNumber();
    public int getMaxNumber();

    /**
     * @return разница между максимальным и минимальным возможным числом
     */
    default int getDiffNumber() {
        return getMaxNumber() - getMinNumber();
    }
}
