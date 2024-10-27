package by.v10k13.quizer.generators;

import by.v10k13.quizer.Task;
import by.v10k13.quizer.TaskGenerator;
import by.v10k13.quizer.exceptions.RunOutOfTasksException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class GroupTaskGenerator implements TaskGenerator<Task> {
    private final ArrayList<TaskGenerator<?>> tasks;
    private final Random random = new Random();

    /**
     * Конструктор с переменным числом аргументов
     *
     * @param generators генераторы, которые в конструктор передаются через запятую
     */
    @SafeVarargs
    public GroupTaskGenerator(TaskGenerator<?>... generators) {
        tasks = new ArrayList<>(List.of(generators));
    }

    /**
     * Конструктор, который принимает коллекцию генераторов
     *
     * @param generators генераторы, которые передаются в конструктор в Collection (например, {@link ArrayList})
     */
    public GroupTaskGenerator(Collection<TaskGenerator<?>> generators) {
        tasks = new ArrayList<>(generators);
    }

    /**
     * @return результат метода generate() случайного генератора из списка.
     *         Если этот генератор выбросил исключение в методе generate(), выбирается другой.
     *         Если все генераторы выбрасывают исключение, то и тут выбрасывается исключение.
     */
    public Task generate() {
        int index = random.nextInt(tasks.size());
        for (int i = 0; i < tasks.size(); i++) {
            try {
                return tasks.get(index).generate();
            }
            catch (RunOutOfTasksException ex) {
                index = (index + 1) % tasks.size();
            }
        }
        throw new RunOutOfTasksException();
    }
}