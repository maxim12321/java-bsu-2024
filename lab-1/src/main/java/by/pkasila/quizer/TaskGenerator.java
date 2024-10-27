package by.pkasila.quizer;

/**
 * TaskGenerator is the interface describing one task generator
 */
public interface TaskGenerator {
    /**
     * Returns a task. If class is immutable, new won't be created
     *
     * @return задание
     * @see    Task
     */
    Task generate();
}
