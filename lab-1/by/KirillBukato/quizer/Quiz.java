package by.KirillBukato.quizer;

import by.KirillBukato.quizer.exceptions.QuizFinishedException;
import by.KirillBukato.quizer.exceptions.QuizNotFinishedException;
import by.KirillBukato.quizer.generators.TaskGenerator;
import by.KirillBukato.quizer.tasks.Task;

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
        this.taskLeft = taskCount;
    }

    /**
     * @return задание, повторный вызов вернет следующее
     * @see Task
     */
    Task nextTask() {
        if (isFinished()) {
            throw new QuizFinishedException("Quiz finished. You can't ask for next task.");
        }
        if (isLastAnswerValid) {
            isLastAnswerValid = false;
            currentTask = generator.generate();
        }
        return currentTask;
    }

    /**
     * Предоставить ответ ученика. Если результат {@link Result#INCORRECT_INPUT}, то счетчик неправильных
     * ответов не увеличивается, а {@link #nextTask()} в следующий раз вернет тот же самый объект {@link Task}.
     */
    Result provideAnswer(String answer) {
        if (isFinished()) {
            throw new QuizFinishedException("Quiz is finished. You can't provide answer.");
        }
        Result result = currentTask.validate(answer);
        isLastAnswerValid = (result != Result.INCORRECT_INPUT);
        taskLeft -= isLastAnswerValid ? 1 : 0;
        answersCounters[result.ordinal()]++;
        return result;
    }

    /**
     * @return завершен ли тест
     */
    boolean isFinished() {
        return taskLeft == 0;
    }

    /**
     * @return количество правильных ответов
     */
    int getCorrectAnswerNumber() {
        return answersCounters[0];
    }

    /**
     * @return количество неправильных ответов
     */
    int getWrongAnswerNumber() {
        return answersCounters[1];
    }

    /**
     * @return количество раз, когда был предоставлен неправильный ввод
     */
    int getIncorrectInputNumber() {
        return answersCounters[2];
    }

    /**
     * @return оценка, которая является отношением количества правильных ответов к количеству всех вопросов.
     * Оценка выставляется только в конце!
     */
    double getMark() {
        if (!isFinished()) {
            throw new QuizNotFinishedException("Quiz is not finished, you can't get the result yet.");
        }
        if (getCorrectAnswerNumber() + getWrongAnswerNumber() == 0) return 1;
        return ((double) getCorrectAnswerNumber()) / (getCorrectAnswerNumber() + getWrongAnswerNumber());
    }

    private final int[] answersCounters = {0, 0, 0};
    private int taskLeft;
    private Task currentTask;
    private boolean isLastAnswerValid = true;
    private final TaskGenerator<? extends Task> generator;
}