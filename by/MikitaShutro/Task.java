/**
 * Interface, который описывает одно задание
 */
package by.MikitaShutro;
public interface Task {
    /**
     @return текст задания
     */
    String getText();

    /**
     * Проверяет ответ на задание и возвращает результат
     *
     * @return результат ответа
     * @see Result
     */
    Result validate(String answer);
}