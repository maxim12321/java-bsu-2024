package by.KirillBukato.quizer.generators;

import by.KirillBukato.quizer.Task;
import by.KirillBukato.quizer.TaskGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Random;

public class GroupTaskGenerator implements TaskGenerator<Task> {
    /**
     * Конструктор с переменным числом аргументов
     *
     * @param generators генераторы, которые в конструктор передаются через запятую
     */
    public GroupTaskGenerator(TaskGenerator<? extends Task>... generators) {
        this.generators = new ArrayList<>(Arrays.asList(generators));
    }

    /**
     * Конструктор, который принимает коллекцию генераторов
     *
     * @param generators генераторы, которые передаются в конструктор в Collection (например, {@link ArrayList})
     */
    public GroupTaskGenerator(Collection<TaskGenerator<? extends Task>> generators) {
        this.generators = generators;
    }

    /**
     * @return результат метода generate() случайного генератора из списка.
     * Если этот генератор выбросил исключение в методе generate(), выбирается другой.
     * Если все генераторы выбрасывают исключение, то и тут выбрасывается исключение.
     */
    @Override
    public Task generate() {
        generators.addAll(removedGenerators);
        removedGenerators.clear();
        Random random = new Random();
        while (!generators.isEmpty()) {
            var iterator = generators.iterator();
            for (int index = random.nextInt(generators.size()); iterator.hasNext(); index--) {
                TaskGenerator<? extends Task> generator = iterator.next();
                if (index == 0) {
                    try {
                        return generator.generate();
                    } catch (RuntimeException e) {
                        removedGenerators.add(generator);
                        iterator.remove();
                        if (generators.isEmpty()) {
                            throw new RuntimeException(e);
                        }
                        break;
                    }
                }
            }
        }
        throw new RuntimeException();
    }

    private final Collection<TaskGenerator<? extends Task>> generators;
    private final Collection<TaskGenerator<? extends Task>> removedGenerators = new ArrayList<>();
}
