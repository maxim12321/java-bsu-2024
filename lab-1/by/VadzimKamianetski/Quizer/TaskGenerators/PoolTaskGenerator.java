package by.VadzimKamianetski.Quizer.TaskGenerators;

import by.VadzimKamianetski.Quizer.Tasks.Task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Collection;

public class PoolTaskGenerator implements TaskGenerator<Task> {
    
    private ArrayList<? extends Task> tasks = new ArrayList<>();
    int index = 0;
    boolean allowDuplicate;

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
        this.tasks = new ArrayList<>(Arrays.asList(tasks));
        this.allowDuplicate = allowDuplicate;
        Collections.shuffle(this.tasks);
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
        this.tasks = new ArrayList<>(tasks);
        Collections.shuffle(this.tasks);
    }

    /**
     * @return случайная задача из списка
     */
    public Task generate() {
        if (!allowDuplicate) {
            if (index == tasks.size()) {
                throw new RuntimeException("There is no samples in PoolTaskGenerator");
            }
            index++;
            return tasks.get(index-1);
        }
        return (tasks.get((int) (Math.random() * (tasks.size()))));
    }
}
