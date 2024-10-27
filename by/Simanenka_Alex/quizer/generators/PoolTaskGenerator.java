package by.Simanenka_Alex.quizer.generators;

import by.Simanenka_Alex.quizer.Task;
import by.Simanenka_Alex.quizer.TaskGenerator;

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
        this.tasks = new ArrayList<>(List.of(tasks));
        this.allowDuplicate = allowDuplicate;
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
        this.tasks = new ArrayList<>(tasks);
        this.allowDuplicate = allowDuplicate;
    }

    /**
     * @return случайная задача из списка
     */
    public Task generate() {
        Random rand = new Random();
        int ind = rand.nextInt(tasks.size());
        if (!allowDuplicate) {
            tasks.remove(ind);
        }
        return (Task) tasks.get(ind);
    }

    private boolean allowDuplicate;
    private ArrayList<Task> tasks;

}