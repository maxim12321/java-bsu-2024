package by.Simanenka_Alex.quizer.generators;

import by.Simanenka_Alex.quizer.Task;
import by.Simanenka_Alex.quizer.TaskGenerator;
import by.Simanenka_Alex.quizer.tasks.MathTask;

import java.util.*;

public class GroupTaskGenerator implements TaskGenerator {
    /**
     * Конструктор с переменным числом аргументов
     *
     * @param generators генераторы, которые в конструктор передаются через запятую
     */
    public GroupTaskGenerator(TaskGenerator... generators) {
        arrList = new ArrayList<>(Arrays.asList(generators));
    }

    /**
     * Конструктор, который принимает коллекцию генераторов
     *
     * @param generators генераторы, которые передаются в конструктор в Collection (например, {@link ArrayList})
     */
    GroupTaskGenerator(Collection<TaskGenerator> generators) {
        arrList = new ArrayList<>(generators);
    }

    /**
     * @return результат метода generate() случайного генератора из списка.
     *         Если этот генератор выбросил исключение в методе generate(), выбирается другой.
     *         Если все генераторы выбрасывают исключение, то и тут выбрасывается исключение.
     */
    public Task generate() throws RuntimeException {
        Random rand = new Random();
        var arrList2 = arrList;
        while (!arrList2.isEmpty()) {
            int ind = rand.nextInt(arrList2.size());
            try {
                return arrList2.get(ind).generate();
            }
            catch (Exception e) {
                arrList2.remove(ind);
            }
        }
        throw new RuntimeException();
    }

    ArrayList<TaskGenerator> arrList;
}