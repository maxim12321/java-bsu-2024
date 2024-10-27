package by.MatsveiZorka.quizer.generators;

import by.MatsveiZorka.quizer.*;
import by.MatsveiZorka.quizer.exceptions.TaskGenerationException;

import java.util.*;

class PoolTaskGenerator<T extends Task> implements TaskGenerator<T> {
    private Iterator<T> Tasks_;

    private void Create_(Collection<T> tasks, boolean isDuplicated) {
        var task = isDuplicated ? tasks : new HashSet<>(tasks);
        validateInput(task);
        Tasks_ = task.iterator();
    }

    private void validateInput(Collection<T> currTasks) {
        for (var i : currTasks)
            validateElement(i);
    }

    private void validateElement(T exactTask) {
        if (exactTask == null)
            throw new NullPointerException("The element is empty");
        if (!(exactTask instanceof T))
            throw new InputTypeMismatchException(exactTask);
    }

    /**
     * Конструктор с переменным числом аргументов
     *
     * @param allowDuplicate разрешить повторения
     * @param tasks          задания, которые в конструктор передаются через запятую
     */
    public PoolTaskGenerator(boolean allowDuplicate, T... tasks) {
        var list = List.of(tasks);
        Create_(list, allowDuplicate);
    }

    /**
     * Конструктор, который принимает коллекцию заданий
     *
     * @param allowDuplicate разрешить повторения
     * @param tasks          задания, которые передаются в конструктор в Collection (например, {@link LinkedList})
     */
    public PoolTaskGenerator(
            boolean allowDuplicate,
            Collection<T> tasks
    ) {
        Create_(tasks, allowDuplicate);
    }

    /**
     * @return случайная задача из списка
     */
    @Override
    public T generate() {
        if (!Tasks_.hasNext())
            throw new TaskGenerationException("No tasks left.");
        return Tasks_.next();
    }

    public static class InputTypeMismatchException extends RuntimeException {
        public InputTypeMismatchException(Object problem) {
            super("Received object with incorrect class: " + problem.getClass().toString());
        }
    }
}