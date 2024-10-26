package by.kirsiv40.quizer.Generators;
import java.util.Collection;
import java.util.concurrent.ThreadLocalRandom;

import by.kirsiv40.quizer.Generators.Math.Exceptions.PoolEmptyException;
import by.kirsiv40.quizer.Tasks.Task;

import java.util.ArrayList;
import java.util.Arrays;

public class PoolTaskGenerator<T extends Task> implements TaskGenerator<T> {
    private final ArrayList<? extends T> tasks;
    private final boolean dup;

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
        this.dup = allowDuplicate;
        for (var i : tasks) {
            if (!(i instanceof Task)) {
                throw new RuntimeException("Not safe varargs");
            }
        }
        this.tasks = new ArrayList<T>(Arrays.asList(tasks));
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
        this.dup = allowDuplicate;
        this.tasks = new ArrayList<T>(tasks);
    }

    /**
     * @return случайная задача из списка
     */
    public T generate() {
        if (tasks.size() == 0) {
            throw new PoolEmptyException("there is no samples in PoolTaskGenerator");
        }
        int num = ThreadLocalRandom.current().nextInt(tasks.size());
        T ans = tasks.get(num);
        if (!dup) {
            tasks.remove(num);
        }
        return ans;
    }
}