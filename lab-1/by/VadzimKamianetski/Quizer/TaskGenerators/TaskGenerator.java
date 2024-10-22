package by.VadzimKamianetski.Quizer.TaskGenerators;

import by.VadzimKamianetski.Quizer.Tasks.Task;
import by.VadzimKamianetski.Quizer.exceptions.PoolGeneratorSamplesException;

/**
 * Interface, который описывает один генератор заданий
 */
public interface TaskGenerator<T extends Task> {
    /**
     * Возвращает задание. При этом новый объект может не создаваться, если класс задания иммутабельный
     *
     * @return задание
     * @throws PoolGeneratorSamplesException 
     * @see    Task
     */
    T generate() throws PoolGeneratorSamplesException;
}