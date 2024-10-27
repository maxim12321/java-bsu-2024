package by.KuryanovichNikita.quizer.generators;

import by.KuryanovichNikita.quizer.exceptions.GroupGeneratorException;
import by.KuryanovichNikita.quizer.exceptions.PoolGeneratorException;
import by.KuryanovichNikita.quizer.tasks.Task;

import java.util.*;

public class PoolTaskGenerator implements TaskGenerator {

    private final List<Task> poolTasks;
    private final boolean allowDuplicate;

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
        poolTasks = new LinkedList<Task>(Arrays.asList(tasks));
        this.allowDuplicate = allowDuplicate;
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
        poolTasks = (List<Task>)(tasks);
        this.allowDuplicate = allowDuplicate;
    }

    /**
     * @return случайная задача из списка
     */
    public Task generate() {
        Random rand = new Random();
        if(poolTasks.isEmpty()){
            throw new PoolGeneratorException("No available tasks to make PoolGenerator");
        }
        int myRandomTaskIndex = rand.nextInt(poolTasks.size());
        Task myRandomTask = poolTasks.get(myRandomTaskIndex);
        if(!allowDuplicate)
            poolTasks.remove(myRandomTaskIndex);
        return myRandomTask;
    }
}
