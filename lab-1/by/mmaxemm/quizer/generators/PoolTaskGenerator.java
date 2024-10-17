package by.mmaxemm.quizer.generators;

import by.mmaxemm.quizer.Task;
import by.mmaxemm.quizer.TaskGenerator;
import java.util.Collection;

public class PoolTaskGenerator implements TaskGenerator {
    /**
     * Конструктор с переменным числом аргументов
     *
     * @param allowDuplicate разрешить повторения
     * @param tasks          задания, которые в конструктор передаются через запятую
     */
    PoolTaskGenerator(
            boolean allowDuplicate,
            Task... tasks
    ) {
        // ...
    }

    /**
     * Конструктор, который принимает коллекцию заданий
     *
     * @param allowDuplicate разрешить повторения
     * @param tasks          задания, которые передаются в конструктор в Collection (например, {@link LinkedList})
     */
    PoolTaskGenerator(
            boolean allowDuplicate,
            Collection<Task> tasks
    ) {
        // ...
    }

    /**
     * @return случайная задача из списка
     */
    public Task generate() {
        return null;
    }
}