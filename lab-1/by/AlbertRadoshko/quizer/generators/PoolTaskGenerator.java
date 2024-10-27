package by.AlbertRadoshko.quizer.generators;

import by.AlbertRadoshko.quizer.Task;
import by.AlbertRadoshko.quizer.TaskGenerator;

import java.util.*;

public class PoolTaskGenerator implements TaskGenerator {
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
        this.tasks = new ArrayList<>(List.of(tasks));
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
        this.allowDuplicate = allowDuplicate;
        this.tasks = new ArrayList<>(tasks);
    }

    /**
     * @return случайная задача из списка
     */
    public Task generate() {
        if (allowDuplicate) {
            Random rand = new Random();
            int i = rand.nextInt(tasks.size());
            return tasks.get(i);
        }
        Collections.shuffle(tasks);
        var task = tasks.getLast();
        tasks.removeLast();
        return task;
    }

    ArrayList<Task> tasks;
    boolean allowDuplicate;
}

