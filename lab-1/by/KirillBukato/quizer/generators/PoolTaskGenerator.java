package by.KirillBukato.quizer.generators;

import by.KirillBukato.quizer.Task;
import by.KirillBukato.quizer.TaskGenerator;

import java.util.*;

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
            Collection<Task> tasks
    ) {
        this.allowDuplicate = allowDuplicate;
        this.tasks = tasks;
    }

    /**
     * @return случайная задача из списка
     */
    @Override
    public Task generate() {
        try {
            Random random = new Random();
            var iterator = tasks.iterator();
            for (int index = random.nextInt(tasks.size()); iterator.hasNext(); index--) {
                Task task = iterator.next();
                if (index == 0) {
                    if (!allowDuplicate) {
                        iterator.remove();
                    }
                    return task;
                }
            }
            throw new RuntimeException();
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Duplicates are not allowed, ran out of tasks.");
        }
        catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    private final boolean allowDuplicate;
    private final Collection<Task> tasks;
}