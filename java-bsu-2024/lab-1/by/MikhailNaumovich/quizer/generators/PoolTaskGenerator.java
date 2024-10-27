package by.MikhailNaumovich.quizer.generators;

import by.MikhailNaumovich.quizer.Task;
import by.MikhailNaumovich.quizer.TaskGenerator;

import by.MikhailNaumovich.quizer.exceptions.InvalidArgumentException;
import by.MikhailNaumovich.quizer.exceptions.InvalidGeneratorException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Random;

import java.util.stream.Collectors;


public class PoolTaskGenerator<T extends Task> implements TaskGenerator<T> {
    /**
     * Конструктор с переменным числом аргументов
     *
     * @param allowDuplicate разрешить повторения
     * @param tasks          задания, которые в конструктор передаются через запятую
     */

    @SafeVarargs
    public PoolTaskGenerator(
        boolean allowDuplicate,
        Task... tasks
    ) { 
        this.random = new Random();
        if (tasks == null) {
            throw new InvalidArgumentException("Task list is null");
        }
        if (tasks.length == 0) {    
            throw new InvalidArgumentException("Empty task list");
        }
        if (Arrays.stream(tasks).anyMatch(Objects::isNull)) {
            throw new InvalidArgumentException("Task list contains null elements");
        }

        this.allowDuplicate = allowDuplicate;
        if (!allowDuplicate) { 
            this.tasks = Arrays.stream(tasks).distinct().map(task -> (T) task).collect(Collectors.toList());
        } else {
            this.tasks = Arrays.stream(tasks).map(task -> (T) task).collect(Collectors.toList());
        }
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
        this.random = new Random();
        if (tasks == null) {
            throw new InvalidArgumentException("Task list is null");
        }
        if (tasks.isEmpty()) {    
            throw new InvalidArgumentException("Empty task list");
        }
        if (tasks.stream().anyMatch(Objects::isNull)) {
            throw new InvalidArgumentException("Task list contains null elements");
        }
        this.allowDuplicate = allowDuplicate;
        if (!allowDuplicate) {
            this.tasks = new ArrayList<>(new LinkedHashSet<>(tasks));
        } else {
            this.tasks = new ArrayList<>(tasks);
        }
        
        Collections.shuffle(this.tasks);
    }

    /**
     * @return случайная задача из списка
     */
    
    public T generate() throws InvalidGeneratorException {
        if (tasks.isEmpty()) {
            throw new InvalidGeneratorException("No tasks available to generate");
        }

        if (allowDuplicate) {
            return tasks.get(random.nextInt(tasks.size()));
        } else {
            currentIndex = random.nextInt(tasks.size());
            T task = tasks.get(currentIndex);
            tasks.remove(currentIndex);
            return task;
        }
    }

    private final boolean allowDuplicate;
    private final List<T> tasks;
    private final Random random;
    private int currentIndex = 0;
}