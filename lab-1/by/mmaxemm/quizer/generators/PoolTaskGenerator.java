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

    PoolTaskGenerator(
            boolean allowDuplicate,
            Task... tasks
    ) {
        this.allowDuplicate = allowDuplicate;
        this.tasks = new ArrayList<>(Arrays.asList(tasks));
    }

    PoolTaskGenerator(
            boolean allowDuplicate,
            Collection<Task> tasks
    ) {
        this.allowDuplicate = allowDuplicate;
        this.tasks = new ArrayList<>(tasks);
    }

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