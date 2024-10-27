package by.maxsavitsky.quizer.generators;

import by.maxsavitsky.quizer.tasks.Task;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class GroupTaskGenerator implements TaskGenerator<Task> {

    private final List<TaskGenerator<Task>> generators;

    /**
     * Конструктор с переменным числом аргументов
     *
     * @param generators генераторы, которые в конструктор передаются через запятую
     */
    @SafeVarargs
    public GroupTaskGenerator(TaskGenerator<Task>... generators) {
        this(List.of(generators));
    }

    /**
     * Конструктор, который принимает коллекцию генераторов
     *
     * @param generators генераторы, которые передаются в конструктор в Collection (например, {@link ArrayList})
     */
    public GroupTaskGenerator(Collection<TaskGenerator<Task>> generators) {
        this.generators = new ArrayList<>(generators);
    }

    /**
     * @return результат метода generate() случайного генератора из списка.
     *         Если этот генератор выбросил исключение в методе generate(), выбирается другой.
     *         Если все генераторы выбрасывают исключение, то и тут выбрасывается исключение.
     */
    @Override
    public Task generate() {
        Collections.shuffle(generators);
        for (var generator : generators) {
            try {
                return generator.generate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        throw new RuntimeException("All generators thrown exceptions");
    }
}
