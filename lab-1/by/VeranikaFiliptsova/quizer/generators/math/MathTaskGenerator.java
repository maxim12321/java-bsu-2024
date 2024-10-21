package by.VeranikaFiliptsova.quizer.generators.math;

import by.VeranikaFiliptsova.quizer.TaskGenerator;

public interface MathTaskGenerator extends TaskGenerator {
    int getMinNumber(); // получить минимальное число
    int getMaxNumber();

    /**
     * @return разница между максимальным и минимальным возможным числом
     */
    default int getDiffNumber()// получить максимальное число
    {
        return 0;
    }

}
