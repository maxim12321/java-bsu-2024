package by.AlexAgeev.quizer.tasks;

/**
 * Задание с заранее заготовленным текстом.
 * Можно использовать {@link PoolTaskGenerator}, чтобы задавать задания такого типа.
 */

import by.AlexAgeev.quizer.Result;
import by.AlexAgeev.quizer.Task;


public class TextTask implements Task {
    /**
     * @param text   текст задания
     * @param answer ответ на задание
     */
    public TextTask(
            String text,
            String answer
    ) {
        this.text = text;
        this.answer = answer;
    }

    public String getText() {
        return this.text;
    };

    public Result validate(String answer) {
            if (!Character.isUpperCase(answer.charAt(0))) {
                System.out.println("Если ответом на задание является слово, то оно должно начинаться с большой буквы!");
            return Result.INCORRECT_INPUT;
        }
      if (this.answer.equals(answer)) {
          return Result.OK;
      } else {
          return Result.WRONG;
      }
    };

    private final String text;
    private final String answer;
}
