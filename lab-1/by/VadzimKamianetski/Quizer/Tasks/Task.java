package by.VadzimKamianetski.Quizer.Tasks;

import by.VadzimKamianetski.Quizer.Result;

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
    Result validate(String answer);
}