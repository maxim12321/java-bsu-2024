package by.v10k13.quizer.generators;

import by.v10k13.quizer.Task;
import by.v10k13.quizer.TaskGenerator;
import by.v10k13.quizer.exceptions.RunOutOfTasksException;

import java.util.*;

public class PoolTaskGenerator implements TaskGenerator<Task> {

    private Iterator<Task> Iterator_;

    private void InitDuplicated_(Collection<Task> src) {

    }

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
        Collection<Task> Tasks_;
        if (allowDuplicate)
            Tasks_ = new ArrayList<>(Arrays.stream(tasks).toList());
        else
            Tasks_ = new HashSet<Task>(Arrays.stream(tasks).toList());
        Iterator_ = Tasks_.iterator();
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
        Collection<Task> Tasks_;

        if (allowDuplicate)
            Tasks_ = new ArrayList<>(tasks);
        else
            Tasks_ = new HashSet<Task>(tasks);

        Iterator_ = Tasks_.iterator();
    }

    /**
     * @return случайная задача из списка
     */
    public Task generate() {
        if(!Iterator_.hasNext())
            throw new RunOutOfTasksException();
        return Iterator_.next();
    }
}