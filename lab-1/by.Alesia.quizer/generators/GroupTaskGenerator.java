package by.Alesia.quizer.generators;

import by.Alesia.quizer.Task;
import by.Alesia.quizer.TaskGenerator;
import by.Alesia.quizer.exceptions.InvalidGeneratorException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class GroupTaskGenerator implements TaskGenerator {
    /**
     * Конструктор с переменным числом аргументов
     *
     * @param generators генераторы, которые в конструктор передаются через запятую
     */
    public GroupTaskGenerator(TaskGenerator... generators) {
        this.generators = new ArrayList<>(Arrays.asList(generators));
        if (this.generators.isEmpty()) {
            throw new InvalidGeneratorException("Empty generators");
        }
    }
    /**
     * Конструктор, который принимает коллекцию генераторов
     *
     * @param generators генераторы, которые передаются в конструктор в Collection (например, {@link ArrayList})
     */
    public GroupTaskGenerator(Collection<TaskGenerator> generators) {
        this.generators = new ArrayList<>(generators);
    }

    /**
     * @return результат метода generate() случайного генератора из списка.
     *         Если этот генератор выбросил исключение в методе generate(), выбирается другой.
     *         Если все генераторы выбрасывают исключение, то и тут выбрасывается исключение.
     */
    public Task generate() throws RuntimeException {
        Collections.shuffle(generators);
        for (TaskGenerator generator : generators) {
            try {
                return generator.generate();
            } catch (RuntimeException _) {
            }
        }
        throw new RuntimeException();
    }


    private final ArrayList<TaskGenerator> generators;
}