package by.VeranikaFiliptsova.quizer.generators.math;

import by.VeranikaFiliptsova.quizer.Task;
import by.VeranikaFiliptsova.quizer.TaskGenerator;
import by.VeranikaFiliptsova.quizer.tasks.math.MathTask;


public interface MathTaskGenerator<T extends MathTask> extends TaskGenerator<T> {

    boolean isNotValid();
    int getMinNumber(); // получить минимальное число
    int getMaxNumber();

    /**
     * @return разница между максимальным и минимальным возможным числом
     */
    default int getDiffNumber()// получить максимальное число
    {
        return getMaxNumber() - getMinNumber();
    }

    int randUsual();

}
//добавить record pair и вынести из генераторов

