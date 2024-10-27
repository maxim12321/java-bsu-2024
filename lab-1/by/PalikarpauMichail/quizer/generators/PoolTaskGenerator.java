package by.PalikarpauMichail.quizer.generators;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Arrays;

import by.PalikarpauMichail.quizer.IntegerRandom;
import by.PalikarpauMichail.quizer.Task;
import by.PalikarpauMichail.quizer.TaskGenerator;
import by.PalikarpauMichail.quizer.exceptions.TaskGenerationException;

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
        this.tasks = new ArrayList<Task>(Arrays.asList(tasks));
        this.allowDuplicate = allowDuplicate;
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
        this.tasks = new ArrayList<>(tasks);
        this.allowDuplicate = allowDuplicate;
    }

    /**
     * @return случайная задача из списка
     */
    public Task generate() {
        if (tasks.isEmpty()) {
            throw new TaskGenerationException();
        }
        int i = IntegerRandom.get(0, tasks.size() - 1);
        Task task = tasks.get(i);
        if (!allowDuplicate) {
            tasks.remove(i);
        }
        return task;
    }

    List<Task> tasks;
    boolean allowDuplicate;
}