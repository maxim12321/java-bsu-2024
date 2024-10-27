package by.KuryanovichNikita.quizer.generators.math;

import by.KuryanovichNikita.quizer.generators.TaskGenerator;

public interface MathTaskGenerator extends TaskGenerator {
    int getMinNumber(); // получить минимальное число
    int getMaxNumber(); // получить максимальное число

    /**
     * @return разница между максимальным и минимальным возможным числом
     */
    default int getDiffNumber(){
        return getMaxNumber() - getMinNumber();
    }
}
