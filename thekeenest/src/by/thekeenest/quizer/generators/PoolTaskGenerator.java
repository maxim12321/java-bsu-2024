package by.thekeenest.quizer.generators;

import by.thekeenest.quizer.tasks.Task;

import java.util.*;

public class PoolTaskGenerator<T extends Task> implements TaskGenerator<T> {
    private final List<T> tasks;
    private final boolean allowDup;
    private final Random random = new Random();

    @SafeVarargs
    public PoolTaskGenerator(boolean allowDup, T... tasks) {
        if (tasks == null || tasks.length == 0) {
            throw new IllegalArgumentException("Tasks are null or empty. ");
        }
        this.allowDup = allowDup;
        this.tasks = new ArrayList<>(Arrays.asList(tasks));
    }

    public PoolTaskGenerator(boolean allowDup, Collection<T> tasks) {
        if (tasks == null || tasks.isEmpty()) {
            throw new IllegalArgumentException("Tasks are null or empty. ");
        }
        this.allowDup = allowDup;
        this.tasks = new ArrayList<>(tasks);
    }

    @Override
    public T generate() {
        if (tasks.isEmpty()) {
            throw new IllegalStateException("No tasks available");
        }
        int i = random.nextInt(tasks.size());
        T task = tasks.get(i);
        if (!allowDup) {
            tasks.remove(i);
        }
        return task;
    }

}
