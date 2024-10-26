package KlimovMikh.quizer.generators;

import KlimovMikh.quizer.Task;
import KlimovMikh.quizer.TaskGenerator;

import java.util.*;

public class PoolTaskGenerator<T extends Task> implements TaskGenerator<T> {
    private final ArrayList<? extends T> taskList;
    private final boolean duplicate;
    private int index = 0;
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
        taskList = new ArrayList<>(List.of(tasks));
        duplicate = allowDuplicate;
        if(!duplicate) {
            Collections.shuffle(taskList);
        }
    }

    /**
     * Конструктор, который принимает коллекцию заданий
     *
     * @param allowDuplicate разрешить повторения
     * @param tasks          задания, которые передаются в конструктор в Collection (например, {@link LinkedList})
     */
    PoolTaskGenerator(
            boolean allowDuplicate,
            Collection<? extends T> tasks
    ) {
        taskList = new ArrayList<>(tasks);
        duplicate = allowDuplicate;
        if(!duplicate) {
            Collections.shuffle(taskList);
        }
    }

    /**
     * @return случайная задача из списка
     */
    public T generate() throws IllegalStateException {
        if(duplicate) {
            Collections.shuffle(taskList);
            return taskList.getFirst();
        }
        if(index < taskList.size()) {
            T task = taskList.get(index);
            ++index;
            return task;
        }
        throw new IllegalStateException("No more KlimovMikh.quizer.tasks in the pool - all KlimovMikh.quizer.tasks have been used");
    }
}
