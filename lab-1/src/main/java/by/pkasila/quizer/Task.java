package by.pkasila.quizer;

/**
 * Task is the interface providing common methods for task
 */
public interface Task {
    /**
     @return the task in the text format
     */
    String getText();

    /**
     * Check the answer to the task and returns result
     *
     * @param  answer ответ на задание
     * @return        результат ответа
     * @see           Result
     */
    Result validate(String answer);
}
