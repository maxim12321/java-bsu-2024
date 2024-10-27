package by.MikitaShutro.generators;

import by.MikitaShutro.TaskGenerator;
import by.MikitaShutro.Task;
import by.MikitaShutro.tasks.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

class GroupTaskGenerator implements TaskGenerator {
    private ArrayList<TaskGenerator> Gens = new ArrayList<>();
    private final Random random = new Random();
    /**
     * Конструктор с переменным числом аргументов
     *
     * @param generators генераторы, которые в конструктор передаются через запятую
     */
    GroupTaskGenerator(TaskGenerator... generators) {
        Gens.addAll(List.of(generators));
    }

    /**
     * Конструктор, который принимает коллекцию генераторов
     *
     * @param generators генераторы, которые передаются в конструктор в Collection (например, {@link ArrayList})
     */
    GroupTaskGenerator(Collection<TaskGenerator> generators) {
        Gens.addAll(generators);
    }

    /**
     * @return результат метода generate() случайного генератора из списка.
     *         Если этот генератор выбросил исключение в методе generate(), выбирается другой.
     *         Если все генераторы выбрасывают исключение, то и тут выбрасывается исключение.
     */
    public Task generate() {
        Integer index = random.nextInt(Gens.size());
        for (int i = 0; i < Gens.size(); i++) {
            try {
                return Gens.get(index).generate();
            }
            catch (RuntimeException ex) {
                index = (index + 1) % Gens.size();
            }
        }
        throw new RuntimeException();
    }
}