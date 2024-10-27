package by.request3301.quizer.generators;

import by.request3301.quizer.Task;
import by.request3301.quizer.TaskGenerator;
import by.request3301.quizer.exceptions.GeneratorExhaustedException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class GroupTaskGenerator<T extends Task> implements TaskGenerator<T> {
    ArrayList<TaskGenerator<? extends T>> generators;
    Random random = new Random();

    /**
     * Конструктор с переменным числом аргументов
     *
     * @param generators генераторы, которые в конструктор передаются через запятую
     */
    @SafeVarargs
    public GroupTaskGenerator(TaskGenerator<? extends T>... generators) {
        this.generators = new ArrayList<>(List.of(generators));
    }

    /**
     * Конструктор, который принимает коллекцию генераторов
     *
     * @param generators генераторы, которые передаются в конструктор в Collection (например, {@link ArrayList})
     */
    public GroupTaskGenerator(Collection<TaskGenerator<? extends T>> generators) {
        this.generators = new ArrayList<>(generators);
    }

    /**
     * @return результат метода generate() случайного генератора из списка.
     * Если этот генератор выбросил исключение в методе generate(), выбирается другой.
     * Если все генераторы выбрасывают исключение, то и тут выбрасывается исключение.
     */
    public T generate() throws GeneratorExhaustedException {
        while (!generators.isEmpty()) {
            int generatorIndex = random.nextInt(generators.size());
            try {
                return generators.get(generatorIndex).generate();
            } catch (GeneratorExhaustedException e) {
                generators.remove(generatorIndex);
            }
        }
        throw new GeneratorExhaustedException("No more generators to call.");
    }
}
