package by.DmitryAntashkevich.quizer.generators.math;

import by.DmitryAntashkevich.quizer.TaskGenerator;

public interface MathTaskGenerator extends TaskGenerator {
    int getMinNumber();
    int getMaxNumber();

    /**
     * @return разница между максимальным и минимальным возможным числом
     */
    default int getDiffNumber() {
        return getMaxNumber() - getMinNumber();
    }

//    boolean isValid();
}
