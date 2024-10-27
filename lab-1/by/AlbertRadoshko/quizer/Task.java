package by.AlbertRadoshko.quizer;

/**
 * Interface, который описывает одно задание
 */
public interface Task {
    /**
     @return текст задания
     */
    String getText();

    /**
     * Проверяет ответ на задание и возвращает результат
     *
     * @param answer ответ на задание
     * @return результат ответа
     * @see Result
     */

    public default Result validate(String answer) {

        return null;
    }
}