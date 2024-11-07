package by.volkovets.quizer;

public enum Result {
    OK, // Получен правильный ответ
    WRONG, // Получен неправильный ответ
    INCORRECT_INPUT // Некорректный ввод. Например, текст, когда ожидалось число
}