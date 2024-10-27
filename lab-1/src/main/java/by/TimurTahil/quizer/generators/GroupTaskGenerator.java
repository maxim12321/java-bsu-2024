package by.TimurTahil.quizer.generators;

import by.TimurTahil.quizer.Task;
import by.TimurTahil.quizer.TaskGenerator;
import by.TimurTahil.quizer.exceptions.AllGeneratorsFailedException;
import by.TimurTahil.quizer.exceptions.NoTasksAreAvailableException;

import java.util.*;


public class GroupTaskGenerator implements TaskGenerator {

    private final List<TaskGenerator> generators;

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
        this.generators = new ArrayList<>(generators);
    }

    /**
     * @return результат метода generate() случайного генератора из списка.
     * Если этот генератор выбросил исключение в методе generate(), выбирается другой.
     * Если все генераторы выбрасывают исключение, то и тут выбрасывается исключение.
     */

    public Task generate() {
        if (generators.isEmpty()) {
            throw new NoTasksAreAvailableException();
        }
        Random random = new Random();
        Set<TaskGenerator> notThrownGenerators = new HashSet<>(generators);
        while (!notThrownGenerators.isEmpty()) {
            int i = random.nextInt(generators.size());
            try {
                return generators.get(i).generate();
            } catch (Exception e) {
                notThrownGenerators.remove(generators.get(i));
            }
        }
        throw new AllGeneratorsFailedException();
    }
}
