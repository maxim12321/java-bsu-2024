package lab1.by.TyapkovArtem.quizer.generators;

import lab1.by.TyapkovArtem.quizer.Task;
import lab1.by.TyapkovArtem.quizer.TaskGenerator;
import lab1.by.TyapkovArtem.quizer.exceptions.GroupTaskGenException;

import java.util.*;

public class GroupTaskGenerator implements TaskGenerator {
    /**
     * Конструктор с переменным числом аргументов
     *
     * @param generators генераторы, которые в конструктор передаются через запятую
     */
    private ArrayList<TaskGenerator> vec_ = new ArrayList<TaskGenerator>();

    public GroupTaskGenerator(TaskGenerator... generators) {
        vec_.addAll(Arrays.asList(generators));
    }

    /**
     * Конструктор, который принимает коллекцию генераторов
     *
     * @param generators генераторы, которые передаются в конструктор в Collection (например, {@link ArrayList})
     */
    public GroupTaskGenerator(Collection<TaskGenerator> generators) {
        vec_.addAll(generators);
    }

    /**
     * @return результат метода generate() случайного генератора из списка.
     *         Если этот генератор выбросил исключение в методе generate(), выбирается другой.
     *         Если все генераторы выбрасывают исключение, то и тут выбрасывается исключение.
     */
    public Task generate() throws RuntimeException{
        Random rand_ = new Random();
        HashSet<Integer> set = new HashSet<>(Set.of());
        while (set.size() < vec_.size()) {
            int b = rand_.ints(0, vec_.size()).filter(a -> !set.contains(a)).findFirst().getAsInt();
            set.add(b);
            try {
                TaskGenerator curgen = vec_.get(b);
                return curgen.generate();
            } catch (RuntimeException _) {
            }
        }
        throw new GroupTaskGenException("xpeHb kakaR To");
    }
}