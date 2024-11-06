package by.AlexAgeev.quizer.generators;

import by.AlexAgeev.quizer.Task;
import by.AlexAgeev.quizer.TaskGenerator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

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
        this.tasks = new ArrayList<>(List.of(tasks));
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
        this.tasks = new ArrayList<>(tasks);
    }

    /**
     * @return случайная задача из списка
     */
    public Task generate() {
        if (tasks.isEmpty()) {
            throw new RuntimeException("No tasks are available");
        }
        Task task;
        Random rand = new Random();
        int i = rand.nextInt(tasks.size());
        task = tasks.get(i);
        if (!allowDuplicate) {
            tasks.remove(i);
        }
        return task;
    }

    private final boolean allowDuplicate;
    private final List<Task> tasks;
}
