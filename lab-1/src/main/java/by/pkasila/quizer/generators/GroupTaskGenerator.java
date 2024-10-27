package by.pkasila.quizer.generators;

import by.pkasila.quizer.Task;
import by.pkasila.quizer.TaskGenerator;
import by.pkasila.quizer.exceptions.QuizException;

import java.util.Collection;

public class GroupTaskGenerator implements TaskGenerator {
    /**
     * Конструктор с переменным числом аргументов
     *
     * @param generators генераторы, которые в конструктор передаются через запятую
     */
    GroupTaskGenerator(TaskGenerator... generators) {
        // ...
    }

    /**
     * Конструктор, который принимает коллекцию генераторов
     *
     * @param generators генераторы, которые передаются в конструктор в Collection (например, {@link ArrayList})
     */
    GroupTaskGenerator(Collection<TaskGenerator> generators) {
        // ...
    }

    /**
     * @return результат метода generate() случайного генератора из списка.
     *         Если этот генератор выбросил исключение в методе generate(), выбирается другой.
     *         Если все генераторы выбрасывают исключение, то и тут выбрасывается исключение.
     */
    public Task generate() {
        throw new QuizException("not implemented");
    }
}
