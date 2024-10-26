package by.MikhailNaumovich.quizer.generators;

import by.MikhailNaumovich.quizer.Task;
import by.MikhailNaumovich.quizer.TaskGenerator;
import by.MikhailNaumovich.quizer.exceptions.InvalidGeneratorException;
import by.MikhailNaumovich.quizer.exceptions.InvalidArgumentException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class GroupTaskGenerator<T extends Task> implements TaskGenerator<T> {
    /**
     * Конструктор с переменным числом аргументов
     *
     * @param generators генераторы, которые в конструктор передаются через запятую
     */
    
    @SafeVarargs
    public GroupTaskGenerator(TaskGenerator<? extends Task>... generators) throws InvalidGeneratorException {
        if (generators == null) {
            throw new InvalidArgumentException("Generators is null");
        }
        if (generators.length == 0) {
            throw new InvalidArgumentException("Generators is empty");
        }
        List<TaskGenerator<? extends Task>> generatorList = Arrays.stream(generators).toList();
        if (generatorList.contains(null)) {
            throw new InvalidGeneratorException("Generators contains null");
        }
         taskGenerators = new ArrayList<>(Arrays.asList(generators));
    }

    /**
     * Конструктор, который принимает коллекцию генераторов
     *
     * @param generators генераторы, которые передаются в конструктор в Collection (например, {@link ArrayList})
     */
    public GroupTaskGenerator(Collection<TaskGenerator<? extends Task>> generators) {
        if (generators == null) {
            throw new IllegalArgumentException("Generators is null");
        }
        if (generators.isEmpty()) {
            throw new IllegalArgumentException("Generators is empty");
        }
        if (generators.contains(null)) {
            throw new IllegalArgumentException("Generators contains null");
        }
        taskGenerators = new ArrayList<>(generators);
    }

    /**
     * @return результат метода generate() случайного генератора из списка.
     *         Если этот генератор выбросил исключение в методе generate(), выбирается другой.
     *         Если все генераторы выбрасывают исключение, то и тут выбрасывается исключение.
     */
    @Override
    public T generate() throws InvalidGeneratorException {
        if (taskGenerators.isEmpty()) {
            throw new InvalidGeneratorException("No tasks available to generate");
        }

        Random random = new Random();

        List<Integer> indices = new ArrayList<>(taskGenerators.size());
        for (int i = 0; i < taskGenerators.size(); i++) {
            indices.add(i);
        }

        while (!indices.isEmpty()) {
            int randomIndex = random.nextInt(indices.size());
            int generatorIndex = indices.get(randomIndex);
            
            try {
                Task generatedTask = taskGenerators.get(generatorIndex).generate();
                if (generatedTask instanceof Task) {
                    return (T) generatedTask;
                } else {
                    throw new InvalidGeneratorException("Generated task is not of the expected type");
                }
            } catch (InvalidGeneratorException e) {
                indices.remove(randomIndex);
            }
        }
        
        throw new InvalidGeneratorException("All generators failed to generate a task");

    }

    private final List<TaskGenerator<? extends Task>> taskGenerators;
}