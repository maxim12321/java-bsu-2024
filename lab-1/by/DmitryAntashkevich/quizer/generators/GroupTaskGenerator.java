package by.DmitryAntashkevich.quizer.generators;

import by.DmitryAntashkevich.quizer.Task;
import by.DmitryAntashkevich.quizer.TaskGenerator;
import by.DmitryAntashkevich.quizer.exceptions.GeneratorException;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

public class GroupTaskGenerator implements TaskGenerator {
    /**
     * Конструктор с переменным числом аргументов
     *
     * @param generators генераторы, которые в конструктор передаются через запятую
     */
    public GroupTaskGenerator(TaskGenerator... generators) {
        this.generators = new ArrayList<>(Arrays.asList(generators));
    }

    /**
     * Конструктор, который принимает коллекцию генераторов
     *
     * @param generators генераторы, которые передаются в конструктор в Collection (например, {@link ArrayList})
     */
    public GroupTaskGenerator(Collection<TaskGenerator> generators) {
        this.generators = generators;
    }

    /**
     * @return результат метода generate() случайного генератора из списка.
     * Если этот генератор выбросил исключение в методе generate(), выбирается другой.
     * Если все генераторы выбрасывают исключение, то и тут выбрасывается исключение.
     */
    @Override
    public Task generate() {
        generators.addAll(removed);
        removed.clear();
        Random random = new Random();
        while (!generators.isEmpty()) {
            var iterator = generators.iterator();
            int index = random.nextInt(generators.size());
            for (; index >= 0; index--) {
                iterator.next();
            }
            var generator = iterator.next();
            try {
                return generator.generate();
            } catch (Exception e) {
                removed.add(generator);
                iterator.remove();
            }
        }
        throw new GeneratorException("Can't generate test");
    }

    private final Collection<TaskGenerator> generators;
    private final Collection<TaskGenerator> removed = new ArrayList<>();
}