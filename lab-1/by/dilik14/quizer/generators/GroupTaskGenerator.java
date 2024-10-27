package by.dilik14.quizer.generators;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import by.dilik14.quizer.exceptions.TaskGeneratorException;
import by.dilik14.quizer.tasks.Task;

public class GroupTaskGenerator<T extends Task> implements TaskGenerator<T> {
    /**
     * Конструктор с переменным числом аргументов
     *
     * @param generators генераторы, которые в конструктор передаются через запятую
     */
    @SafeVarargs
    public GroupTaskGenerator(TaskGenerator<? extends T>... generators) {
        this.generators = new ArrayList<>(Arrays.asList(generators));
    }

    /**
     * Конструктор, который принимает коллекцию генераторов
     *
     * @param generators генераторы, которые передаются в конструктор в Collection
     *                   (например, {@link ArrayList})
     */

    public GroupTaskGenerator(Collection<TaskGenerator<? extends T>> generators) {
        this.generators = new ArrayList<>(generators);
    }

    /**
     * @return результат метода generate() случайного генератора из списка.
     *         Если этот генератор выбросил исключение в методе generate(),
     *         выбирается другой.
     *         Если все генераторы выбрасывают исключение, то и тут выбрасывается
     *         исключение.
     */
    @Override
    public T generate() throws RuntimeException {
        Random random = new Random();
        RuntimeException lastException = null;

        for (int i = 0; i < generators.size(); i++) {
            TaskGenerator<? extends T> generator = generators.get(random.nextInt(generators.size()));
            try {
                return generator.generate();
            } catch (RuntimeException e) {
                lastException = e;
            }
        }

        throw lastException != null ? lastException : new TaskGeneratorException("No generators available");
    }

    private final List<TaskGenerator<? extends T>> generators;
}
