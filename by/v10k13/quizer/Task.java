package by.v10k13.quizer;

public interface Task {
    public static enum Result {
        OK, // Получен правильный ответ
        WRONG, // Получен неправильный ответ
        INCORRECT_INPUT // Некорректный ввод. Например, текст, когда ожидалось число
    }

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