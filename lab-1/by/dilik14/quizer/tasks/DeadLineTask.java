package by.dilik14.quizer.tasks;

import by.dilik14.quizer.Result;

/**
 * Задание с заранее заготовленным текстом на умение умножать и сравнивать
 * числа.
 */
public class DeadLineTask implements Task {
    /**
     * @param lhs         кол-во заданий
     * @param rhs         длительность заданий в часах
     * @param deadline    кол-во часов до дедлайна
     */
    public DeadLineTask(
            int lhs,
            int rhs,
            int deadline) {
        this.lhs = lhs;
        this.rhs = rhs;
        this.deadline = deadline;
        check();
    }

    @Override
    public String getText() {
        return "Успеет ли Паша сделать " + String.valueOf(lhs) + " заданий по " + String.valueOf(rhs)
                + " часов каждое, если до дедлайна осталось " + String.valueOf(deadline) + " часов? : (Да/Нет)";
    }

    public Result validate(String answer) {
        if (!answer.equals("Да") && !answer.equals("Нет")) {
            return Result.INCORRECT_INPUT;
        }

        if ((answer.equals("Да")) == (lhs * rhs <= deadline)) {
            return Result.OK;
        } else {
            return Result.WRONG;
        }
    }

    public void check() {
        if (lhs <= 0 && rhs <= 0 || lhs > rhs) { // Дедлайн спокойно может уйти в минус, а вот кол-во или длительность заданий -
                                    // никогда ;(
            throw new IllegalArgumentException("Incorrect arguments");
        }
    }

    protected final int lhs;
    protected final int rhs;
    protected final int deadline;
}
