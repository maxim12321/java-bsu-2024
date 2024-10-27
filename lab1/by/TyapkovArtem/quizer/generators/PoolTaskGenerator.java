package lab1.by.TyapkovArtem.quizer.generators;

import lab1.by.TyapkovArtem.quizer.*;

import java.util.*;

public class PoolTaskGenerator implements TaskGenerator {
    /**
     * Конструктор с переменным числом аргументов
     *
     * @param allowDuplicate разрешить повторения
     * @param tasks          задания, которые в конструктор передаются через запятую
     */
    private ArrayList<Task> vec_ = new ArrayList<Task>();
    private boolean allowduplo_ = false;


    public PoolTaskGenerator(boolean allowDuplicate, Task... tasks) {
        allowduplo_ = allowDuplicate;
        for (var task : tasks) {
            if (!vec_.contains(task) || allowduplo_) {
                vec_.add(task);
            }
        }
    }

    /**
     * Конструктор, который принимает коллекцию заданий
     *
     * @param allowDuplicate разрешить повторения
     * @param tasks          задания, которые передаются в конструктор в Collection (например, {@link LinkedList})
     */
    public PoolTaskGenerator(
            boolean allowDuplicate,
            Collection<Task> tasks
    ) {
        allowduplo_ = allowDuplicate;
        tasks.stream().filter(a -> !vec_.contains(a) || allowduplo_).forEach(a -> vec_.add(a));
    }

    /**
     * @return случайная задача из списка
     */
    public Task generate() {
        Random rand = new Random();
        return vec_.get(rand.nextInt());
    }
}