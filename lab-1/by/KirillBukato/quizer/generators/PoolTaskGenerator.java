package by.KirillBukato.quizer.generators;

import by.KirillBukato.quizer.Task;
import by.KirillBukato.quizer.TaskGenerator;
import by.KirillBukato.quizer.exceptions.PoolGeneratorRanOutException;

import java.util.*;

public class PoolTaskGenerator<T extends Task> implements TaskGenerator<T> {
    /**
     * Конструктор с переменным числом аргументов
     *
     * @param allowDuplicate разрешить повторения
     * @param tasks          задания, которые в конструктор передаются через запятую
     */

    @SafeVarargs
    public PoolTaskGenerator(
            boolean allowDuplicate,
            T... tasks
    ) {
        this.allowDuplicate = allowDuplicate;
        this.tasks = new ArrayList<>(Arrays.asList(tasks));
    }

    /**
     * Конструктор, который принимает коллекцию заданий
     *
     * @param allowDuplicate разрешить повторения
     * @param tasks          задания, которые передаются в конструктор в Collection (например, {@link LinkedList})
     */
    public PoolTaskGenerator(
            boolean allowDuplicate,
            Collection<T> tasks
    ) {
        this.allowDuplicate = allowDuplicate;
        this.tasks = new ArrayList<>(tasks);
    }

    /**
     * @return случайная задача из списка
     */
    @Override
    public T generate() throws PoolGeneratorRanOutException {
        if (tasks.isEmpty()) {
            throw new PoolGeneratorRanOutException("Pool Task Generator has no tasks left.");
        }
        Random random = new Random();
        int index = random.nextInt(tasks.size());
        T task = tasks.get(index);
        if (!allowDuplicate) {
            tasks.remove(index);
        }
        return task;
    }

    private final boolean allowDuplicate;
    private final ArrayList<T> tasks;
}