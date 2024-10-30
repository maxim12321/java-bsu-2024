package by.SanchukS.quizer.generators;

import by.SanchukS.quizer.Task;
import by.SanchukS.quizer.TaskGenerator;
import by.SanchukS.quizer.exceptions.GenerateException;
import by.SanchukS.quizer.exceptions.NullArgumentException;

import java.util.*;

class GroupTaskGenerator implements TaskGenerator {
    private final List<TaskGenerator> taskGeneratorList;

    private final Random random = new Random();

    /**
     * Конструктор с переменным числом аргументов
     *
     * @param generators генераторы, которые в конструктор передаются через запятую
     */
    GroupTaskGenerator(TaskGenerator... generators) {
        if (generators == null) throw new NullArgumentException("generators");
        taskGeneratorList = Arrays.stream(generators).toList();
    }

    /**
     * Конструктор, который принимает коллекцию генераторов
     *
     * @param generators генераторы, которые передаются в конструктор в Collection (например, {@link ArrayList})
     */
    GroupTaskGenerator(Collection<TaskGenerator> generators) {
        if (generators == null) throw new NullArgumentException("generators");
        taskGeneratorList = new ArrayList<>(generators);
    }

    /**
     * @return результат метода generate() случайного генератора из списка.
     *         Если этот генератор выбросил исключение в методе generate(), выбирается другой.
     *         Если все генераторы выбрасывают исключение, то и тут выбрасывается исключение.
     */
    public Task generate() {
        if (taskGeneratorList.isEmpty()) throw new GenerateException("No task generators found");
        return taskGeneratorList.get(random.nextInt(taskGeneratorList.size())).generate();
    }
}