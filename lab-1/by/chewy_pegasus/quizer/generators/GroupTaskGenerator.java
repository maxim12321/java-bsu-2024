package by.chewy_pegasus.quizer.generators;

import by.chewy_pegasus.quizer.Task;
import by.chewy_pegasus.quizer.TaskGenerator;
import by.chewy_pegasus.quizer.generators.math.AbstractMathTaskGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.concurrent.ThreadLocalRandom;

public class GroupTaskGenerator implements TaskGenerator {
    /**
     * Конструктор с переменным числом аргументов
     *
     * @param generators генераторы, которые в конструктор передаются через запятую
     */
    ArrayList<TaskGenerator> gens;
    public GroupTaskGenerator(TaskGenerator... generators) {
        gens = new ArrayList<>(generators.length);
        Arrays.stream(generators)
                .distinct()
                .forEach(val -> gens.add(val));
    }

    /**
     * Конструктор, который принимает коллекцию генераторов
     *
     * @param generators генераторы, которые передаются в конструктор в Collection (например, {@link ArrayList})
     */
    GroupTaskGenerator(Collection<TaskGenerator> generators) {
        gens = new ArrayList<>(generators.size());
        generators.stream()
                .distinct()
                .forEach(val -> gens.add(val));
    }

    /**
     * @return результат метода generate() случайного генератора из списка.
     *         Если этот генератор выбросил исключение в методе generate(), выбирается другой.
     *         Если все генераторы выбрасывают исключение, то и тут выбрасывается исключение.
     */
    public Task generate() {
        HashSet<TaskGenerator> tried = new HashSet<>(gens.size());
        int x = 0;
        Task task = null;
        while (true) {
            try {
                x = ThreadLocalRandom.current().nextInt(0, gens.size());
                task = (Task) gens.get(x).generate();
                break;
            } catch (Exception e) {
                tried.add(gens.get(x));
                if (tried.size() >= gens.size()) {
                    throw new RuntimeException("GroupTaskGenerator: all generators threw exception");
                }
            }
        }
        return task;
    }
}
