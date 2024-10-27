package by.maxsavitsky.quizer.generators;

import by.maxsavitsky.quizer.exceptions.PoolTaskEmptyException;
import by.maxsavitsky.quizer.tasks.Task;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class PoolTaskGenerator implements TaskGenerator<Task> {

    private final boolean allowDuplicates;
    private final List<Task> tasks;
    private final Random random = new Random();

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
        this(allowDuplicate, List.of(tasks));
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
        this.allowDuplicates = allowDuplicate;
        this.tasks = new ArrayList<>(tasks);
    }

    /**
     * @return случайная задача из списка
     */
    @Override
    public Task generate() {
        if (allowDuplicates) {
            return tasks.get(random.nextInt(tasks.size()));
        }
        if (tasks.isEmpty()) {
            throw new PoolTaskEmptyException();
        }
        int ind = random.nextInt(tasks.size());
        Task task = tasks.get(ind);
        tasks.remove(ind);
        return task;
    }
}
