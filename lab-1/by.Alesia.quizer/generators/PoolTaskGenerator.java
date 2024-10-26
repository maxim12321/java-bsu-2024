package by.Alesia.quizer.generators;

import by.Alesia.quizer.Task;
import by.Alesia.quizer.TaskGenerator;
import by.Alesia.quizer.exceptions.InvalidGeneratorException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Random;

public class PoolTaskGenerator implements TaskGenerator {
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
        allowDuplicate_ = allowDuplicate;
        tasks_ = new ArrayList<>(Arrays.asList(tasks));
    }

    /**
     * Конструктор, который принимает коллекцию заданий
     *
     * @param allowDuplicate разрешить повторения
     * @param tasks          задания, которые передаются в конструктор в Collection (например, {@link LinkedList})
     */
    PoolTaskGenerator(
            boolean allowDuplicate,
            Collection<Task> tasks, boolean allowDuplicate1, ArrayList<Task> tasks1
    ) {
        allowDuplicate_ = allowDuplicate1;
        tasks_ = tasks1;
    }

    /**
     * @return случайная задача из списка
     */
    public Task generate() throws InvalidGeneratorException {
        if (tasks_.isEmpty()) {
            throw new InvalidGeneratorException("Empty generators");
        }
        int temp = (new Random()).nextInt(tasks_.size());
        Task currentTask = tasks_.get(temp);
        if (!allowDuplicate_) {
            tasks_.remove(temp);
        }
        return currentTask;
    }

    private final boolean allowDuplicate_;
    private final ArrayList<Task> tasks_;
}