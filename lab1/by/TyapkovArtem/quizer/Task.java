package lab1.by.TyapkovArtem.quizer;

/**
 * Interface, который описывает одно задание
 */
public interface Task {
    /**
     @return текст задания
     */
    public String getText();

    /**
     * Проверяет ответ на задание и возвращает результат
     *
     * @param  answer ответ на задание
     * @return        результат ответа
     * @see           by.TyapkovArtyom.quizer.Result
     */
    public Result validate(String answer);
}