package by.AlbertRadoshko.quizer.generators;

import by.AlbertRadoshko.quizer.Task;
import by.AlbertRadoshko.quizer.TaskGenerator;
import by.AlbertRadoshko.quizer.exceptions.GeneratorException;
import by.AlbertRadoshko.quizer.tasks.TextTask;

import java.util.ArrayList;
import java.util.*;

public class GroupTaskGenerator implements TaskGenerator {
    /**
     * Конструктор с переменным числом аргументов
     *
     * @param generators генераторы, которые в конструктор передаются через запятую
     */
    public GroupTaskGenerator(TaskGenerator... generators) {
        this.generators = new ArrayList<>(Arrays.asList(generators));
    }

    /**
     * Конструктор, который принимает коллекцию генераторов
     *
     * @param generators генераторы, которые передаются в конструктор в Collection (например, {@link ArrayList})
     */
    public GroupTaskGenerator(Collection<TaskGenerator> generators) {
        this.generators = generators;
    }

    /**
     * @return результат метода generate() случайного генератора из списка.
     *         Если этот генератор выбросил исключение в методе generate(), выбирается другой.
     *         Если все генераторы выбрасывают исключение, то и тут выбрасывается исключение.
     */
    public Task generate() {
        ArrayList<TaskGenerator> curGenerators = new ArrayList<>(generators);
        while (!generators.isEmpty()) {
            Collections.shuffle(curGenerators);
            try {
                return curGenerators.getLast().generate();
            } catch (Exception e) {
                curGenerators.removeLast();
            }
        }
        throw new GeneratorException("Failed to generate task");
    }

    Collection<TaskGenerator> generators;
    Collection<TaskGenerator> removed = new ArrayList<>();
}
