package by.KirillBukato.quizer.generators;

import by.KirillBukato.quizer.Task;
import by.KirillBukato.quizer.TaskGenerator;
import by.KirillBukato.quizer.exceptions.InvalidGeneratorException;

import java.util.*;

public class GroupTaskGenerator implements TaskGenerator<Task> {
    /**
     * Конструктор с переменным числом аргументов
     *
     * @param generators генераторы, которые в конструктор передаются через запятую
     */
    @SafeVarargs
    public GroupTaskGenerator(TaskGenerator<? extends Task>... generators) throws InvalidGeneratorException {
        this.generators = new ArrayList<>(Arrays.asList(generators));
        if (this.generators.isEmpty()) {
            throw new InvalidGeneratorException("GroupTaskGenerator must have at least one generator");
        }
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
     * Если этот генератор выбросил исключение в методе generate(), выбирается другой.
     * Если все генераторы выбрасывают исключение, то и тут выбрасывается исключение.
     */
    @Override
    public Task generate() throws RuntimeException {
        Collections.shuffle(generators);
        RuntimeException exception = new RuntimeException();
        for (TaskGenerator<? extends Task> generator : generators) {
            try {
                return generator.generate();
            } catch (RuntimeException e) {
                exception = e;
            }
        }
        throw exception;
    }

    private final ArrayList<TaskGenerator<? extends Task>> generators;
}
