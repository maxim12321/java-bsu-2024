package by.DmitryAntashkevich.quizer.generators;

import by.DmitryAntashkevich.quizer.Task;
import by.DmitryAntashkevich.quizer.TaskGenerator;
import by.DmitryAntashkevich.quizer.exceptions.GeneratorException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Random;
import java.util.LinkedList;

public class PoolTaskGenerator<T extends Task> implements TaskGenerator<T> {
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
        this.tasks = new ArrayList<>(Arrays.asList(tasks));
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
            Collection<T> tasks
    ) {
        this.tasks = new ArrayList<>(tasks);
        this.allowDuplicate = allowDuplicate;
    }

    /**
     * @return случайная задача из списка
     */
    @Override
    public T generate() {
        if (tasks.isEmpty()) {
            throw new GeneratorException("No tasks to choose from");
        }
        Random random = new Random();
        int index = random.nextInt(tasks.size());
        T task = tasks.get(index);
        if (!allowDuplicate) {
            tasks.remove(index);
        }
        return task;
    }

    final private ArrayList<T> tasks;
    final private boolean allowDuplicate;
}