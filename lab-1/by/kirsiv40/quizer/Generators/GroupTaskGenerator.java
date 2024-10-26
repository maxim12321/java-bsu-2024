package by.kirsiv40.quizer.Generators;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.ThreadLocalRandom;

import by.kirsiv40.quizer.Generators.Math.Exceptions.PoolEmptyException;
import by.kirsiv40.quizer.Tasks.Task;

public class GroupTaskGenerator<T extends Task> implements TaskGenerator<T> {
    private final ArrayList<TaskGenerator<? extends T>> generatorsPool;

    /**
     * Конструктор с переменным числом аргументов
     *
     * @param generators генераторы, которые в конструктор передаются через запятую
     */
    @SafeVarargs
    public GroupTaskGenerator(TaskGenerator<? extends T>... generators) { // Помним про HeapPollution
        for (var i : generators) {
            if (!(i instanceof TaskGenerator<? extends Task>)) {
                throw new RuntimeException("Not safe varargs");
            }
        }
        generatorsPool = new ArrayList<>(Arrays.asList(generators));
    }

    /**
     * Конструктор, который принимает коллекцию генераторов
     *
     * @param generators генераторы, которые передаются в конструктор в Collection (например, {@link ArrayList})
     */
    public GroupTaskGenerator(Collection<TaskGenerator<? extends T>> generators) {
        generatorsPool = new ArrayList<>(generators);
    }

    /**
     * @return результат метода generate() случайного генератора из списка.
     *         Если этот генератор выбросил исключение в методе generate(), выбирается другой.
     *         Если все генераторы выбрасывают исключение, то и тут выбрасывается исключение.
     */
    public T generate() {
        int genNumber;
        while (generatorsPool.size() != 0) {
            genNumber = ThreadLocalRandom.current().nextInt(generatorsPool.size());
            try {
                return generatorsPool.get(genNumber).generate();
            } catch(RuntimeException e) {
                generatorsPool.remove(genNumber);
            }
        }
        throw new PoolEmptyException("There is no valid generators in the group generator");
    }
}