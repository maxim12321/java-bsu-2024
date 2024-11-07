package by.volkovets.quizer.generators;

import by.volkovets.quizer.TaskGenerator;
import by.volkovets.quizer.Task;

import java.util.LinkedList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Random;

public class PoolTaskGenerator implements TaskGenerator<Task> {

    private final boolean allowDuplicate;
    private final LinkedList<Task> tasks;
    private HashSet<Integer> used;

    public PoolTaskGenerator(
            boolean AllowDuplicate,
            Task... Tasks) {
        this.allowDuplicate = AllowDuplicate;
        this.tasks = new LinkedList<Task>();

        for (Task task : Tasks) {
            this.tasks.add(task);
        }

        this.used = new HashSet<>();
    }

    PoolTaskGenerator(
            boolean allowDuplicate,
            Collection<Task> tasks) {
        this.allowDuplicate = allowDuplicate;
        this.tasks = new LinkedList<Task>();

        for (Task task : tasks) {
            this.tasks.add(task);
        }

        this.used = new HashSet<Integer>();
    }

    @Override
    public Task generate() {

        if (!allowDuplicate) {
            // if (this.used.size() == this.tasks.size()) {
            //     throw new RuntimeException("All tasks from the pool are used");
            // }
            Random random = new Random();
            int index;

            do {
                index = random.nextInt(this.tasks.size());
            } while (used.contains(index));
            this.used.add(index);

            return this.tasks.get(index);

        }

        Random random = new Random();
        int index = random.nextInt(this.tasks.size());
        return this.tasks.get(index);
    }

    public Boolean DuplicatesAvailable() {
        return this.allowDuplicate;
    }

    public int TasksInPool() {
        return this.tasks.size();
    }
}