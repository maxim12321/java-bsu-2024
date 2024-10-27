/**
 * Interface, который описывает один генератор заданий
 */
package by.MikitaShutro;
public interface TaskGenerator<T extends Task> {
    /**
     * Возвращает задание. При этом новый объект может не создаваться, если класс задания иммутабельный
     *
     * @return задание
     * @see    Task
     */
    T generate();
}