package by.DmitryAntashkevich.quizer.generators;

import by.DmitryAntashkevich.quizer.Task;
import by.DmitryAntashkevich.quizer.TaskGenerator;
import by.DmitryAntashkevich.quizer.exceptions.GeneratorException;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

public class GroupTaskGenerator<T extends Task> implements TaskGenerator<T> {
    /**
     * Конструктор с переменным числом аргументов
     *
     * @param generators генераторы, которые в конструктор передаются через запятую
     */
    @SafeVarargs
    public GroupTaskGenerator(TaskGenerator<? extends T>... generators) {
        this.generators = new ArrayList<>(Arrays.asList(generators));
    }

    /**
     * Конструктор, который принимает коллекцию генераторов
     *
     * @param generators генераторы, которые передаются в конструктор в Collection (например, {@link ArrayList})
     */
    public GroupTaskGenerator(Collection<TaskGenerator<? extends T>> generators) {
        this.generators = new ArrayList<>(generators);
    }

    /**
     * @return результат метода generate() случайного генератора из списка.
     * Если этот генератор выбросил исключение в методе generate(), выбирается другой.
     * Если все генераторы выбрасывают исключение, то и тут выбрасывается исключение.
     */
    @Override
    public T generate() {
        generators.addAll(removed);
        removed.clear();
        Random random = new Random();
        while (!generators.isEmpty()) {
            int index = random.nextInt(generators.size());
            try {
                return generators.get(index).generate();
            } catch (Exception e) {
                removed.add(generators.get(index));
                generators.remove(index);
            }
        }
        throw new GeneratorException("All generators failed generation");
    }

    private final ArrayList<TaskGenerator<? extends T>> generators;
    private final ArrayList<TaskGenerator<? extends T>> removed = new ArrayList<>();
}