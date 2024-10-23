package by.MatsveiZorka.quizer.generators;

import by.MatsveiZorka.quizer.*;
import by.MatsveiZorka.quizer.exceptions.TaskGenerationException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GroupTaskGenerator implements TaskGenerator {
    ArrayList<TaskGenerator> Tasks_;
    /**
     * Конструктор с переменным числом аргументов
     *
     * @param generators генераторы, которые в конструктор передаются через запятую
     */
    public GroupTaskGenerator(TaskGenerator... generators) {
        Tasks_ = new ArrayList<>(List.of(generators));
    }

    /**
     * Конструктор, который принимает коллекцию генераторов
     *
     * @param generators генераторы, которые передаются в конструктор в Collection (например, {@link ArrayList})
     */
    public GroupTaskGenerator(Collection<TaskGenerator> generators) {
        Tasks_ = new ArrayList<>(generators);
    }

    /**
     * @return результат метода generate() случайного генератора из списка.
     *         Если этот генератор выбросил исключение в методе generate(), выбирается другой.
     *         Если все генераторы выбрасывают исключение, то и тут выбрасывается исключение.
     */
    public Task generate() {
        if (Tasks_.isEmpty())
            throw new TaskGenerationException("No tasks left");

        int src_index = ((int)Math.floor(Math.random() * Tasks_.size()));
        for (int i = 0; i < Tasks_.size(); i++) {
            try {
                return Tasks_.get((i + src_index) % Tasks_.size()).generate();
            } catch (TaskGenerationException _) {}
        }
        throw new TaskGenerationException("No tasks left");
    }
}