package by.AlexAgeev.quizer.generators;

import by.AlexAgeev.quizer.Task;
import by.AlexAgeev.quizer.TaskGenerator;
import by.AlexAgeev.quizer.exceptions.AllGeneratorsFailedException;
import by.AlexAgeev.quizer.exceptions.NoGeneratorsAvailableException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class GroupTaskGenerator implements TaskGenerator<Task> {
    /**
     * Конструктор с переменным числом аргументов
     *
     * @param generators генераторы, которые в конструктор передаются через запятую
     */
    @SafeVarargs
    public GroupTaskGenerator(TaskGenerator<? extends Task>... generatorsToConstruct) {
        this.generators = new ArrayList<>();
        Collections.addAll(generators, generatorsToConstruct);
    }

    /**
     * Конструктор, который принимает коллекцию генераторов
     *
     * @param generators генераторы, которые передаются в конструктор в Collection (например, {@link ArrayList})
     */
    public GroupTaskGenerator(Collection<TaskGenerator<? extends Task>> generators) {
        this.generators = new ArrayList<>(generators);
    }

    /**
     * @return результат метода generate() случайного генератора из списка.
     *         Если этот генератор выбросил исключение в методе generate(), выбирается другой.
     *         Если все генераторы выбрасывают исключение, то и тут выбрасывается исключение.
     */
    @Override
    public Task generate() throws RuntimeException {
        if (generators.isEmpty()) {
            throw new NoGeneratorsAvailableException();
        }
        for (int i = 0; i < generators.size(); i++) {
            try {
                return generators.get(i).generate();
            } catch (Exception e) {
                System.err.println("Generator[" + i + "] has failed");
            }
        }
        throw new AllGeneratorsFailedException();
    }

private final List<TaskGenerator<? extends Task>> generators;
}