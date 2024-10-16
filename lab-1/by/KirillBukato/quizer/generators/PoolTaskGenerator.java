package by.KirillBukato.quizer.generators;

import by.KirillBukato.quizer.Task;
import by.KirillBukato.quizer.TaskGenerator;

import java.util.*;

public class PoolTaskGenerator implements TaskGenerator<Task> {
    /**
     * Конструктор с переменным числом аргументов
     *
     * @param allowDuplicate разрешить повторения
     * @param tasks          задания, которые в конструктор передаются через запятую
     */
    public PoolTaskGenerator(
            boolean allowDuplicate,
            Task... tasks
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
            Collection<Task> tasks
    ) {
        this.allowDuplicate = allowDuplicate;
        this.tasks = tasks;
    }

    /**
     * @return случайная задача из списка
     */
    @Override
    public Task generate() {
        if (tasks.isEmpty()) {
            throw new IllegalArgumentException("Pool Task Generator has no tasks left.");
        }
        Random random = new Random();
        var iterator = tasks.iterator();
        for (int index = random.nextInt(tasks.size()); index > 0; index--) {
            iterator.next();
        }
        Task task = iterator.next();
        if (!allowDuplicate) {
            iterator.remove();
        }
        return task;
    }

    private final boolean allowDuplicate;
    private final Collection<Task> tasks;
}