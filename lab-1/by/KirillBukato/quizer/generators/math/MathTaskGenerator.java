package by.KirillBukato.quizer.generators.math;

import by.KirillBukato.quizer.TaskGenerator;

public interface MathTaskGenerator extends TaskGenerator {
    
    int getMinNumber();
    
    int getMaxNumber();

    RuntimeException validateGenerator();

    /**
     * @return разница между максимальным и минимальным возможным числом
     */
    default int getDiffNumber() {
        return getMaxNumber() - getMinNumber();
    }
}
