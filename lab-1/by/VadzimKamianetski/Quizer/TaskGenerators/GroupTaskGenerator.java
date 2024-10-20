package by.VadzimKamianetski.Quizer.TaskGenerators;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import by.VadzimKamianetski.Quizer.Tasks.Task;

public class GroupTaskGenerator implements TaskGenerator<Task> {
    private ArrayList<TaskGenerator<? extends Task>> generators = new ArrayList<>();

    /**
     * Конструктор с переменным числом аргументов
     *
     * @param generators генераторы, которые в конструктор передаются через запятую
     */
    @SuppressWarnings("unchecked")
    public GroupTaskGenerator(TaskGenerator<? extends Task>... generators) {
        this.generators = new ArrayList<>(Arrays.asList(generators));
        Collections.shuffle(this.generators);
    }

    /**
     * Конструктор, который принимает коллекцию генераторов
     *
     * @param generators генераторы, которые передаются в конструктор в Collection (например, {@link ArrayList})
     */
    public GroupTaskGenerator(Collection<TaskGenerator<? extends Task>> generators) {
        this.generators = new ArrayList<>(generators);
        Collections.shuffle(this.generators);
    }

    /**
     * @return результат метода generate() случайного генератора из списка.
     *         Если этот генератор выбросил исключение в методе generate(), выбирается другой.
     *         Если все генераторы выбрасывают исключение, то и тут выбрасывается исключение.
     */
    @Override
    public Task generate() {
        Task task;
        int index = (int) (Math.random()*generators.size());
        try {
            task = (generators.get(index)).generate();
        } catch(RuntimeException e) {
            generators.remove(index);
            if (0 != generators.size()) {
                task = generate();
            } else {
                throw e;
            }
        }
        return task;
    }
}