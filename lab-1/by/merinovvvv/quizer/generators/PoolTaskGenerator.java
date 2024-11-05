package by.merinovvvv.quizer.generators;

import by.merinovvvv.quizer.Task;
import by.merinovvvv.quizer.TaskGenerator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class PoolTaskGenerator <T extends Task> implements TaskGenerator<T> {

    private final boolean allowDuplicate;
    private final List<T> tasks;
    private final List<T> usedTasks;
    private final Random random;

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
        this.tasks = new ArrayList<>(List.of(tasks));
        usedTasks = new ArrayList<>();
        random = new Random();
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
        usedTasks = new ArrayList<>();
        random = new Random();
    }

    /**
     * @return случайная задача из списка
     */
    public T generate() {

        if (tasks.isEmpty() || !allowDuplicate && usedTasks.size() == tasks.size()) {
            throw new RuntimeException("No tasks available to generate");
        }

        T task;
        do {
            int index = random.nextInt(tasks.size());
            task = tasks.get(index);
        } while (!allowDuplicate && usedTasks.contains(task));

        if (!allowDuplicate) {
            usedTasks.add(task);
        }

        return task;
    }
}