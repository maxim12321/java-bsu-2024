package by.MikhailNaumovich.quizer;

import by.MikhailNaumovich.quizer.exceptions.InvalidArgumentException;

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
     * @param  answer ответ на задание
     * @return        результат ответа
     * @see           Result
     */
    Result validate(String answer) throws InvalidArgumentException;
}