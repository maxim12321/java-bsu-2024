package by.request3301.quizer;

import by.request3301.quizer.exceptions.QuizNotFinishedException;

public class Quiz {
    TaskGenerator<? extends Task> generator;
    int tasksLeft;
    int correctAnswersNumber = 0;
    int wrongAnswersNumber = 0;
    int incorrectInputNumber = 0;
    Task currentTask;
    boolean incorrectInput = false;

    /**
     * @param generator генератор заданий
     * @param taskCount количество заданий в тесте
     */
    public Quiz(TaskGenerator<? extends Task> generator, int taskCount) {
        this.generator = generator;
        tasksLeft = taskCount;
    }

    /**
     * @return задание, повторный вызов вернет слелующее
     * @see Task
     */
    public Task nextTask() {
        if (!incorrectInput) {
            currentTask = generator.generate();
            --tasksLeft;
        }
        return currentTask;
    }

    /**
     * Предоставить ответ ученика. Если результат {@link Result#INCORRECT_INPUT}, то счетчик неправильных
     * ответов не увеличивается, а {@link #nextTask()} в следующий раз вернет тот же самый объект {@link Task}.
     */
    public Result provideAnswer(String answer) {
        Result result = currentTask.validate(answer);
        if (result == Result.OK) {
            incorrectInput = false;
            ++correctAnswersNumber;
        } else if (result == Result.WRONG) {
            incorrectInput = false;
            ++wrongAnswersNumber;
        } else {
            incorrectInput = true;
            ++incorrectInputNumber;
        }
        return result;
    }

    /**
     * @return завершен ли тест
     */
    public boolean isFinished() {
        return tasksLeft == 0;
    }

    /**
     * @return количество правильных ответов
     */
    public int getCorrectAnswerNumber() {
        return correctAnswersNumber;
    }

    /**
     * @return количество неправильных ответов
     */
    public int getWrongAnswerNumber() {
        return wrongAnswersNumber;
    }

    /**
     * @return количество раз, когда был предоставлен неправильный ввод
     */
    public int getIncorrectInputNumber() {
        return incorrectInputNumber;
    }

    /**
     * @return оценка, которая является отношением количества правильных ответов к количеству всех вопросов.
     * Оценка выставляется только в конце!
     */
    public double getMark() {
        if (!isFinished()) {
            throw new QuizNotFinishedException("Called getMark() before quiz finished");
        }
        return (double) getCorrectAnswerNumber() / (getCorrectAnswerNumber() + getWrongAnswerNumber());
    }
}
