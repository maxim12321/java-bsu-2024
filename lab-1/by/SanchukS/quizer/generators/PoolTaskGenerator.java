package by.SanchukS.quizer.generators;

import by.SanchukS.quizer.Task;
import by.SanchukS.quizer.TaskGenerator;
import by.SanchukS.quizer.exceptions.GenerateException;
import by.SanchukS.quizer.exceptions.NullArgumentException;

import java.util.*;

public class PoolTaskGenerator implements TaskGenerator<Task> {
    private final List<Task> tasks;
    private final boolean allowDuplicate;

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
        if (tasks == null) throw new NullArgumentException("tasks");
        this.allowDuplicate = allowDuplicate;
        this.tasks = Arrays.stream(tasks).toList();
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
        if (tasks == null) throw new NullArgumentException("tasks");
        this.allowDuplicate = allowDuplicate;
        this.tasks = new ArrayList<>(tasks);
    }

    /**
     * @return случайная задача из списка
     */
    public Task generate() {
        if (tasks.isEmpty()) throw new GenerateException("No tasks to generate");
        int taskPos = random.nextInt(tasks.size());
        Task resultTask = tasks.get(taskPos);
        if (!allowDuplicate) {
            tasks.remove(taskPos);
        }
        return resultTask;
    }
}