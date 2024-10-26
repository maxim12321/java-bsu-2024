package KlimovMikh.quizer.generators;

import KlimovMikh.quizer.Task;
import KlimovMikh.quizer.TaskGenerator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class GroupTaskGenerator implements TaskGenerator<Task> {
    private final ArrayList<TaskGenerator<? extends Task>> taskGenerators;
    /**
     * Конструктор с переменным числом аргументов
     *
     * @param generators генераторы, которые в конструктор передаются через запятую
     */
    @SafeVarargs
    public GroupTaskGenerator(TaskGenerator<? extends Task>... generators) {
        taskGenerators = new ArrayList<>(List.of(generators));
    }

    /**
     * Конструктор, который принимает коллекцию генераторов
     *
     * @param generators генераторы, которые передаются в конструктор в Collection (например, {@link ArrayList})
     */
    GroupTaskGenerator(Collection<TaskGenerator<? extends Task>> generators) {
        taskGenerators = new ArrayList<>(generators);
    }

    /**
     * @return результат метода generate() случайного генератора из списка.
     *         Если этот генератор выбросил исключение в методе generate(), выбирается другой.
     *         Если все генераторы выбрасывают исключение, то и тут выбрасывается исключение.
     */
    public Task generate() {
        Collections.shuffle(taskGenerators);
        int i = 0;
        while (i < taskGenerators.size()) {
            try {
                return taskGenerators.get(i).generate();
            } catch(Exception ex) {
                if(i == taskGenerators.size() - 1) {
                    throw ex;
                }
                ++i;
            }
        }
        return null;
    }
}
