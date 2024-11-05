package generators;

import core.Task;
import core.TaskGenerator;
import exceptions.EndOfPoolException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Random;

public class PoolTaskGenerator implements TaskGenerator<Task> {
    private final boolean allowDuplicate;
    private final Collection<Task> tasks;
    public PoolTaskGenerator(
            boolean allowDuplicate,
            Task... tasks
    ) {
        this.allowDuplicate = allowDuplicate;
        this.tasks = new ArrayList<>(Arrays.asList(tasks));
    }

    public PoolTaskGenerator(
            boolean allowDuplicate,
            Collection<Task> tasks
    ) {
        this.allowDuplicate = allowDuplicate;
        this.tasks = tasks;
    }

    public int getSize() {
        return tasks.size();
    }

    public boolean getAllowDuplicates() {
        return allowDuplicate;
    }

    @Override
    public Task generate() throws EndOfPoolException {
        Random random = new Random();
        if (tasks.isEmpty()) {
            throw new EndOfPoolException("Generation of task is impossible, pool of tasks is empty");
        }
        var iterator = tasks.iterator();
        for (int index = random.nextInt(tasks.size()); index > 0; index--) {
            iterator.next();
        }
        Task task = iterator.next();
        if (!allowDuplicate) {
            iterator.remove();
        }
        return task;
    }
}