package by.chewy_pegasus.quizer.generators;

import by.chewy_pegasus.quizer.Task;
import by.chewy_pegasus.quizer.TaskGenerator;
import by.chewy_pegasus.quizer.generators.math.AbstractMathTaskGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.concurrent.ThreadLocalRandom;

public class PoolTaskGenerator implements TaskGenerator {
    private final ArrayList<Task> tasks;
    private final boolean allowDuplicates;
    private final HashSet<Task> used;
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
        this.allowDuplicates = allowDuplicate;
        this.tasks = new ArrayList<>(tasks.length);
        used = new HashSet<>(tasks.length);
        if (!this.allowDuplicates) {
            Arrays.stream(tasks)
                    .parallel()
                    .filter(val -> {
                        if (this.used.contains(val)) {
                            return false;
                        }
                        this.used.add(val);
                        return true;
                    })
                    .forEach(this.tasks::add);
        } else {
            Arrays.stream(tasks)
                    .parallel()
                    .forEach(this.tasks::add);
        }
    }

    /**
     * Конструктор, который принимает коллекцию заданий
     *
     * @param allowDuplicate разрешить повторения
     * @param tasks          задания, которые передаются в конструктор в Collection (например, {@link //LinkedList})
     */
    PoolTaskGenerator(
            boolean allowDuplicate,
            Collection<Task> tasks
    ) {
        this.allowDuplicates = allowDuplicate;
        this.tasks = new ArrayList<>(tasks.size());
        used = new HashSet<>(tasks.size());
        if (!this.allowDuplicates) {
            tasks.stream()
                    .parallel()
                    .filter(val -> {
                        if (this.used.contains(val)) {
                            return false;
                        }
                        this.used.add(val);
                        return true;
                    })
                    .forEach(this.tasks::add);
        } else {
            tasks.stream()
                    .parallel()
                    .forEach(this.tasks::add);
        }
    }

    /**
     * @return случайная задача из списка
     */
    public Task generate() {
        if (!this.allowDuplicates) {
            if (tasks.isEmpty()) {
                throw new RuntimeException("PoolTaskGenerator: required task count is bigger than pool size");
            }
            int x = ThreadLocalRandom.current().nextInt(0, tasks.size());
            Task task = tasks.get(x);
            tasks.remove(x);
            return task;
        } else {
            return tasks.get(ThreadLocalRandom.current().nextInt(0, tasks.size()));
        }
    }
}
