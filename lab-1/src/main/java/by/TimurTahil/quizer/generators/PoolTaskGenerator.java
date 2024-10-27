package by.TimurTahil.quizer.generators;

import by.TimurTahil.quizer.Task;
import by.TimurTahil.quizer.TaskGenerator;
import by.TimurTahil.quizer.exceptions.NoTasksAreAvailableException;

import java.util.*;


public class PoolTaskGenerator implements TaskGenerator {

    private final boolean allowDuplicate;
    private final List<Task> tasks;

    /**
     * Конструктор с переменным числом аргументов
     *
     * @param allowDuplicate разрешить повторения
     * @param tasks          задания, которые в конструктор передаются через запятую
     */
    public PoolTaskGenerator(boolean allowDuplicate, Task... tasks) {
        this.allowDuplicate = allowDuplicate;
        this.tasks = new ArrayList<>(Arrays.asList(tasks));
    }

    /**
     * Конструктор, который принимает коллекцию заданий
     *
     * @param allowDuplicate разрешить повторения
     * @param tasks          задания, которые передаются в конструктор в Collection (например, {@link LinkedList})
     */
    public PoolTaskGenerator(boolean allowDuplicate, Collection<Task> tasks) {
        this.allowDuplicate = allowDuplicate;
        this.tasks = new ArrayList<>(tasks);
    }

    /**
     * @return случайная задача из списка
     */
    public Task generate() {
        if (tasks.isEmpty()) {
            throw new NoTasksAreAvailableException();
        }
        Random random = new Random();
        final int i = random.nextInt(tasks.size());
        final Task task = tasks.get(i);
        if (!allowDuplicate) {
            tasks.remove(i);
        }
        return task;
    }
}
