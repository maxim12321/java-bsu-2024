package by.merinovvvv.quizer.tasks;

import by.merinovvvv.quizer.Result;
import by.merinovvvv.quizer.Task;

public class AppleTask implements Task {

    int num1;
    int num2;

    public AppleTask(
            int minNumber,
            int maxNumber
    ) {

        do {
            num2 = (int) (Math.random() * (maxNumber - minNumber + 1) + minNumber);
        } while (num2 < 0);

        do {
            num1 = (int) (Math.random() * (maxNumber - minNumber + 1) + minNumber);
        } while (num1 < num2);

    }

    @Override
    public String getText() {
        return "У A было " + num1 + " яблок(а), он(она) подарил(а) B " + num2 + " Y яблок(а). Сколько яблок осталось у A?";
    }

    @Override
    public Result validate(String answer) {

        int correctAnswer = num1 - num2;

        try {
            double userAnswer = Double.parseDouble(answer);
            return userAnswer == correctAnswer ? Result.OK : Result.WRONG;
        } catch (NumberFormatException e) {
            return Result.INCORRECT_INPUT;
        }
    }
}
