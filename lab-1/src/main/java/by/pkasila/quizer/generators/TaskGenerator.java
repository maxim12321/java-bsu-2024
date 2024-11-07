package by.pkasila.quizer.generators;

import by.pkasila.quizer.common.Task;

/**
 * Interface, который описывает один генератор заданий
 */
public interface TaskGenerator<T extends Task> {
    /**
     * Возвращает задание. При этом новый объект может не создаваться, если класс задания иммутабельный
     *
     * @return задание
     * @see    Task
     */
    T generate();
}
