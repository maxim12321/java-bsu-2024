package by.dilik14.quizer;

import by.dilik14.quizer.exceptions.QuizFinishedException;
import by.dilik14.quizer.exceptions.QuizNotFinishedException;
import by.dilik14.quizer.generators.TaskGenerator;
import by.dilik14.quizer.tasks.Task;

/**
 * Class, который описывает один тест
 */
class Quiz {

    /**
     * @param generator генератор заданий
     * @param taskCount количество заданий в тесте
     */
    Quiz(TaskGenerator<? extends Task> generator, int taskCount) {
        this.generator = generator;
        this.taskCount = taskCount;
    }

    /**
     * @return задание, повторный вызов вернет слелующее
     * @see Task
     */
    Task nextTask() {
        if (isFinished()) {
            throw new QuizFinishedException("No tasks left");
        }

        if (!LastWasIncorr) {
            curTask = generator.generate();
        } else {
            LastWasIncorr = false;
        }

        return curTask;
    }

    /**
     * Предоставить ответ ученика. Если результат {@link Result#INCORRECT_INPUT}, то
     * счетчик неправильных
     * ответов не увеличивается, а {@link #nextTask()} в следующий раз вернет тот же
     * самый объект {@link Task}.
     */
    Result provideAnswer(String answer) {
        Result res = curTask.validate(answer);
        if (res == Result.OK) {
            OkCount += 1;
            taskCount -= 1;
        } else if (res == Result.WRONG) {
            WrongCount += 1;
            taskCount -= 1;
        } else {
            LastWasIncorr = true;
            IncorrInpCount += 1;
        }

        return res;
    }

    /**
     * @return завершен ли тест
     */
    boolean isFinished() {
        return (taskCount == 0);
    }

    /**
     * @return количество правильных ответов
     */
    int getCorrectAnswerNumber() {
        return OkCount;
    }

    /**
     * @return количество неправильных ответов
     */
    int getWrongAnswerNumber() {
        return WrongCount;
    }

    /**
     * @return количество раз, когда был предоставлен неправильный ввод
     */
    int getIncorrectInputNumber() {
        return IncorrInpCount;
    }

    /**
     * @return оценка, которая является отношением количества правильных ответов к
     *         количеству всех вопросов.
     *         Оценка выставляется только в конце!
     */
    double getMark() {
        if (!isFinished()) {
            throw new QuizNotFinishedException("Quiz is not finished");
        }

        return 1.0 * OkCount / (OkCount + WrongCount);
    }

    private final TaskGenerator<? extends Task> generator;
    private int taskCount;
    private int OkCount = 0;
    private int WrongCount = 0;
    private int IncorrInpCount = 0;
    private boolean LastWasIncorr = false;

    private Task curTask = null;
}
