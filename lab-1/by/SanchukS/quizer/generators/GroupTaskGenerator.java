package by.SanchukS.quizer.generators;

import by.SanchukS.quizer.Task;
import by.SanchukS.quizer.TaskGenerator;
import by.SanchukS.quizer.exceptions.GenerateException;
import by.SanchukS.quizer.exceptions.NullArgumentException;

import java.util.*;

public class GroupTaskGenerator implements TaskGenerator {
    private List<TaskGenerator> taskGeneratorList;

    private final Random random = new Random();

    /**
     * Конструктор с переменным числом аргументов
     *
     * @param generators генераторы, которые в конструктор передаются через запятую
     */
    public GroupTaskGenerator(TaskGenerator... generators) {
        if (generators == null) throw new NullArgumentException("generators");
        taskGeneratorList = new ArrayList<>(Arrays.asList(generators));
    }

    /**
     * Конструктор, который принимает коллекцию генераторов
     *
     * @param generators генераторы, которые передаются в конструктор в Collection (например, {@link ArrayList})
     */
    public GroupTaskGenerator(Collection<TaskGenerator> generators) {
        if (generators == null) throw new NullArgumentException("generators");
        taskGeneratorList = new ArrayList<>(generators);
    }

    public boolean addTaskGenerator(TaskGenerator taskGenerator) {
        return taskGeneratorList.add(taskGenerator);
    }

    /**
     * @return результат метода generate() случайного генератора из списка.
     *         Если этот генератор выбросил исключение в методе generate(), выбирается другой.
     *         Если все генераторы выбрасывают исключение, то и тут выбрасывается исключение.
     */
    @Override
    public Task generate() {
        if (taskGeneratorList.isEmpty()) throw new GenerateException("Zero task generators");
        Collections.shuffle(taskGeneratorList);
        for (var generator : taskGeneratorList) {
            try {
                return generator.generate();
            }
            catch (GenerateException ignored) {}
        }
        throw new GenerateException("Zero task generators available");
    }
}