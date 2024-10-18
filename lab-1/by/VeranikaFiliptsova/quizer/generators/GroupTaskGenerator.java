package by.VeranikaFiliptsova.quizer.generators;

import by.VeranikaFiliptsova.quizer.Task;
import by.VeranikaFiliptsova.quizer.TaskGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class GroupTaskGenerator implements TaskGenerator {
    ArrayList<TaskGenerator> list = new ArrayList<>();
    /**
     * Конструктор с переменным числом аргументов
     *
     * @param generators генераторы, которые в конструктор передаются через запятую
     */
    GroupTaskGenerator(TaskGenerator... generators) {
        list = new ArrayList<>(Arrays.asList(generators));
    }

    /**
     * Конструктор, который принимает коллекцию генераторов
     *
     * @param generators генераторы, которые передаются в конструктор в Collection (например, {@link ArrayList})
     */
    GroupTaskGenerator(Collection<TaskGenerator> generators) {
        list.addAll(generators);
    }

    /**
     * @return результат метода generate() случайного генератора из списка.
     *         Если этот генератор выбросил исключение в методе generate(), выбирается другой.
     *         Если все генераторы выбрасывают исключение, то и тут выбрасывается исключение.
     */
    public Task generate() {
        for (TaskGenerator gen : list) {
            Task task;
            try {
                task = gen.generate();
            } catch (RuntimeException ex) {
                continue;
            }
            return task;
        }
        throw new RuntimeException();
    }
}