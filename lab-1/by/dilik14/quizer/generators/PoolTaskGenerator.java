package by.dilik14.quizer.generators;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import by.dilik14.quizer.exceptions.TaskGeneratorException;
import by.dilik14.quizer.tasks.Task;

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
    public T generate() throws RuntimeException{
        if (tasks.isEmpty()) {
            throw new TaskGeneratorException("No tasks to choose from");
        }
        Random random = new Random();
        int task_ind = random.nextInt(tasks.size());
        T task = tasks.get(task_ind);
        if (!allowDuplicate) {
            tasks.remove(task_ind);
        }
        return task;
    }

    private final boolean allowDuplicate;
    private final List<T> tasks;
}

