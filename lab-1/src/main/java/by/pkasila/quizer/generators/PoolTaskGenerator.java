package by.pkasila.quizer.generators;

import by.pkasila.quizer.exceptions.EmptyPoolGeneratorException;
import by.pkasila.quizer.common.Task;

import java.util.*;

public class PoolTaskGenerator<T extends Task> implements TaskGenerator<T> {

    private final boolean allowDuplicate;

    private final ArrayList<T> tasks;

    @SafeVarargs
    public PoolTaskGenerator(
            boolean allowDuplicate,
            T... tasks
    ) {
        this.allowDuplicate = allowDuplicate;
        this.tasks = new ArrayList<>(Arrays.asList(tasks));
    }

    public PoolTaskGenerator(
            boolean allowDuplicate,
            Collection<T> tasks
    ) {
        this.allowDuplicate = allowDuplicate;
        this.tasks = new ArrayList<>(tasks);
    }

    @Override
    public T generate() throws EmptyPoolGeneratorException {
        if (tasks.isEmpty()) {
            throw new EmptyPoolGeneratorException("Pool Task Generator has no tasks left.");
        }
        Random random = new Random();
        int index = random.nextInt(tasks.size());
        T task = tasks.get(index);
        if (!allowDuplicate) {
            tasks.remove(index);
        }
        return task;
    }
}
