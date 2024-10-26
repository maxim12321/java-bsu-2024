package by.kirsiv40.quizer.Generators.Math;

import by.kirsiv40.quizer.Generators.TaskGenerator;
import by.kirsiv40.quizer.Tasks.Task;
import by.kirsiv40.quizer.Tasks.Math.MathTask;

public interface MathTaskGenerator<T extends MathTask> extends TaskGenerator<T> {
    public int getMinNumber();
    public int getMaxNumber();

    /**
     * @return разница между максимальным и минимальным возможным числом
     */
    public default int getDiffNumber() {
        return getMaxNumber() - getMinNumber();
    }

    /**
     * Возвращает задание. При этом новый объект может не создаваться, если класс задания иммутабельный
     *
     * @return задание
     * @see    Task
     */
    T generate();
}
