package by.mmaxemm.quizer.generators;

import by.mmaxemm.quizer.Task;
import by.mmaxemm.quizer.TaskGenerator;

import java.util.Arrays;
import java.util.Collection;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;

public class PoolTaskGenerator implements TaskGenerator {
    boolean allowDuplicate;
    List<Task> tasks;
    Random random;

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
        this.tasks = new ArrayList<>(Arrays.asList(tasks));
        this.random = new Random();
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
        this.random = new Random();
    }

    /**
     * @return случайная задача из списка
     */
    public Task generate() {
        if(tasks.isEmpty()) {
            throw new IllegalStateException("No tasks in the pool");
        }
        if(!allowDuplicate) {
            int i = random.nextInt(tasks.size());
            Task task = tasks.get(i);
            tasks.remove(i);
            return task;
        } else {
            int i = random.nextInt(tasks.size());
            Task task = tasks.get(i);
            return task;
        }
    }
}