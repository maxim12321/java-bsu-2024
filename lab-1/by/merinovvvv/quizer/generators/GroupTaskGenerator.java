package by.merinovvvv.quizer.generators;

import by.merinovvvv.quizer.Task;
import by.merinovvvv.quizer.TaskGenerator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GroupTaskGenerator<T extends Task> implements TaskGenerator<T> {
    /**
     * Конструктор с переменным числом аргументов
     *
     * @param generators генераторы, которые в конструктор передаются через запятую
     */
    private final List<TaskGenerator<T>> generatorList;
    private final Random random;

    @SafeVarargs
    public GroupTaskGenerator(TaskGenerator<T>... generators) {
        generatorList = new ArrayList<>();
        Collections.addAll(generatorList, generators);
        this.random = new Random();
    }

    /**
     * Конструктор, который принимает коллекцию генераторов
     *
     * @param generators генераторы, которые передаются в конструктор в Collection (например, {@link ArrayList})
     */
    public GroupTaskGenerator(Collection<TaskGenerator<T>> generators) {
        generatorList = new ArrayList<>(generators);
        this.random = new Random();
    }

    /**
     * @return результат метода generate() случайного генератора из списка.
     *         Если этот генератор выбросил исключение в методе generate(), выбирается другой.
     *         Если все генераторы выбрасывают исключение, то и тут выбрасывается исключение.
     */
    public T generate() {
        if (generatorList.isEmpty()) {
            throw new IllegalArgumentException("No generators available");
        }

        List<Integer> triedIndexes = new ArrayList<>();

        while (triedIndexes.size() < generatorList.size()) {
            int randomIndex = random.nextInt(generatorList.size());
            if (triedIndexes.contains(randomIndex)) {
                continue;
            }
            triedIndexes.add(randomIndex);
            try {
                return generatorList.get(randomIndex).generate();
            } catch (RuntimeException e) {
                System.err.println("Generator at index " + randomIndex + " failed: " + e.getMessage());
            }
        }
        throw new RuntimeException("All generators failed to generate a task");
    }
}