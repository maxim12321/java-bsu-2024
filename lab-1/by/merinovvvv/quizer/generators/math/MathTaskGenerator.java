package by.merinovvvv.quizer.generators.math;

import by.merinovvvv.quizer.TaskGenerator;
import by.merinovvvv.quizer.tasks.math.MathTask;

public interface MathTaskGenerator extends TaskGenerator<MathTask> {
    Object[] generateMathTask(int maxNumber, int minNumber,
                              MathTask.Operation operation, boolean monkey);

    int getMinNumber(); // получить минимальное число
    int getMaxNumber(); // получить максимальное число

    /**
     * @return разница между максимальным и минимальным возможным числом
     */
    default int getDiffNumber() {
        return (getMaxNumber() - getMinNumber());
    }
}
