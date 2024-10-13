package by.v10k13.quizer.generators;

import by.v10k13.quizer.Task;
import by.v10k13.quizer.TaskGenerator;
import by.v10k13.quizer.exceptions.RunOutOfTasksException;
import by.v10k13.quizer.tasks.math.AbstractMathTask;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

class GroupTaskGenerator implements TaskGenerator<Task> {
    private final ArrayList<TaskGenerator<Task>> tasks;
    private final Random random = new Random();

    /**
     * Конструктор с переменным числом аргументов
     *
     * @param generators генераторы, которые в конструктор передаются через запятую
     */
    public GroupTaskGenerator(TaskGenerator<Task>... generators) {
        tasks = new ArrayList<>(List.of(generators));
    }

    /**
     * Конструктор, который принимает коллекцию генераторов
     *
     * @param generators генераторы, которые передаются в конструктор в Collection (например, {@link ArrayList})
     */
    GroupTaskGenerator(Collection<TaskGenerator<Task>> generators) {
        tasks = new ArrayList<>(generators);
    }

    /**
     * @return результат метода generate() случайного генератора из списка.
     *         Если этот генератор выбросил исключение в методе generate(), выбирается другой.
     *         Если все генераторы выбрасывают исключение, то и тут выбрасывается исключение.
     */
    public Task generate() {
        int index = random.nextInt();
        for (int i = 0; i < tasks.size(); i++) {
            try {
                return tasks.get((index + i) % tasks.size()).generate();
            }
            catch (RunOutOfTasksException ex) {}
        }
        throw new RunOutOfTasksException();
    }
}