package by.pkasila.quizer.generators;

import by.pkasila.quizer.Task;
import by.pkasila.quizer.TaskGenerator;
import by.pkasila.quizer.exceptions.QuizException;

import java.util.Collection;
import java.util.LinkedList;

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
        throw new QuizException("not implemented");
    }
}
