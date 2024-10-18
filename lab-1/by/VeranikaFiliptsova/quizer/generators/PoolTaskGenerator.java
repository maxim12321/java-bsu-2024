package by.VeranikaFiliptsova.quizer.generators;

import by.VeranikaFiliptsova.quizer.Task;
import by.VeranikaFiliptsova.quizer.TaskGenerator;

import java.util.*;

public class PoolTaskGenerator implements TaskGenerator {
    ArrayList<Task> list = new ArrayList<>();
    boolean duplicate;
    /**
     * Конструктор с переменным числом аргументов
     *
     * @param allowDuplicate разрешить повторения
     * @param tasks          задания, которые в конструктор передаются через запятую
     */
    PoolTaskGenerator(
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
    PoolTaskGenerator(
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
