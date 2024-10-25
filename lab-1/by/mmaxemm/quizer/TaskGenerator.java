package by.mmaxemm.quizer;

import by.mmaxemm.quizer.exceptions.TaskGenerationException;

public interface TaskGenerator {
    /**
     * Возвращает задание. При этом новый объект может не создаваться, если класс задания иммутабельный
     *
     * @return задание
     * @see    Task
     */
    Task generate() throws TaskGenerationException;
}
