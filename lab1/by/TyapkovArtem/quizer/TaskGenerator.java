package lab1.by.TyapkovArtem.quizer;

/**
 * Interface, который описывает один генератор заданий
 */
public interface TaskGenerator {
    /**
     * Возвращает задание. При этом новый объект может не создаваться, если класс задания иммутабельный
     *
     * @return задание
     * @see    by.TyapkovArtyom.quizer.Task
     */
    public Task generate();
}