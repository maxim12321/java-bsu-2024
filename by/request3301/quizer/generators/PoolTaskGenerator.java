package by.request3301.quizer.generators;

import by.request3301.quizer.Task;
import by.request3301.quizer.TaskGenerator;
import by.request3301.quizer.exceptions.GeneratorExhaustedException;

import java.util.*;

public class PoolTaskGenerator<T extends Task> implements TaskGenerator<T> {
    Random random = new Random();
    int currentTask = -1;
    boolean allowDuplicates;
    ArrayList<T> tasks;

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
        this.allowDuplicates = allowDuplicate;
        this.tasks = new ArrayList<>(List.of(tasks));
        Collections.shuffle(this.tasks);
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
        this.allowDuplicates = allowDuplicate;
        this.tasks = new ArrayList<>(tasks);
    }

    /**
     * @return случайная задача из списка
     */
    public T generate() throws GeneratorExhaustedException {
        if (!allowDuplicates) {
            ++currentTask;
            if (currentTask >= tasks.size()) {
                throw new GeneratorExhaustedException("Used all the tasks in the pool.");
            }
            return tasks.get(currentTask);
        }
        int taskIndex = random.nextInt(tasks.size());
        return tasks.get(taskIndex);
    }
}
