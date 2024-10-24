package by.VeranikaFiliptsova.quizer.generators;

import by.VeranikaFiliptsova.quizer.Task;
import by.VeranikaFiliptsova.quizer.TaskGenerator;

import java.util.*;

public class PoolTaskGenerator implements TaskGenerator<Task> {
    ArrayList<Task> list = new ArrayList<>();
    boolean duplicate;
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
        list = new ArrayList<>(Arrays.asList(tasks));
        duplicate = allowDuplicate;
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
        list.addAll(tasks);
        duplicate = allowDuplicate;
    }

    /**
     * @return случайная задача из списка
     */
    public Task generate() {
        Random rand = new Random();
        if (list.isEmpty()) {
            throw new RuntimeException("The list of tasks in the pool is empty");
        }
        int steps = rand.nextInt(list.size());
        var iter = list.iterator();
        while (steps > 0) {
            iter.next();
            steps--;
        }
        Task task = iter.next();
        if (!duplicate){
            iter.remove();
        }
        return task;
    }
}
