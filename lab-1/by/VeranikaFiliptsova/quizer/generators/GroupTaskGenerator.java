package by.VeranikaFiliptsova.quizer.generators;

import by.VeranikaFiliptsova.quizer.Task;
import by.VeranikaFiliptsova.quizer.TaskGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Random;

public class GroupTaskGenerator implements TaskGenerator<Task> {
    ArrayList<TaskGenerator<? extends Task>> list = new ArrayList<>();
    /**
     * Конструктор с переменным числом аргументов
     *
     * @param generators генераторы, которые в конструктор передаются через запятую
     */
    @SafeVarargs
    public GroupTaskGenerator(TaskGenerator<? extends Task>... generators) {
        list = new ArrayList<>(Arrays.asList(generators));
    }

    /**
     * Конструктор, который принимает коллекцию генераторов
     *
     * @param generators генераторы, которые передаются в конструктор в Collection (например, {@link ArrayList})
     */
    public GroupTaskGenerator(Collection<TaskGenerator<? extends Task>> generators) {
        list.addAll(generators);
    }

    /**
     * @return результат метода generate() случайного генератора из списка.
     *         Если этот генератор выбросил исключение в методе generate(), выбирается другой.
     *         Если все генераторы выбрасывают исключение, то и тут выбрасывается исключение.
     */
    public Task generate() {
        while (!list.isEmpty()) {
            Random rand = new Random();
            int i = rand.nextInt(list.size());
            TaskGenerator<? extends Task> gen = list.get(i);
            Task task;
            try {
                task = gen.generate();
            } catch (RuntimeException ex) {
                list.remove(i);
                continue;
            }
            return task;
        }
        throw new RuntimeException("impossible to generate valid task for all generators");
    }
}