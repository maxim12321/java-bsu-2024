package by.pkasila.quizer.common;

import by.pkasila.quizer.exceptions.FinishedQuizException;
import by.pkasila.quizer.exceptions.NoTaskException;
import by.pkasila.quizer.exceptions.UnfinishedQuizException;
import by.pkasila.quizer.generators.TaskGenerator;

/**
 * Class, который описывает один тест
 */
public class Quiz {
    private final TaskGenerator<? extends Task> generator;
    private final int taskCount;

    private int tasksGiven = 0;

    private Task currentTask;

    private int correctAnswersNumber = 0;
    private int wrongAnswerNumber = 0;
    private int incorrectInputNumber = 0;

    /**
     * @param generator генератор заданий
     * @param taskCount количество заданий в тесте
     */
    public Quiz(TaskGenerator<? extends Task> generator, int taskCount) {
        this.generator = generator;
        this.taskCount = taskCount;
    }

    /**
     * @return задание, повторный вызов вернет слелующее
     * @see Task
     */
    public Task nextTask() throws FinishedQuizException {
        if (this.isFinished()) {
            throw new FinishedQuizException("you cannot request next task");
        }

        if (this.currentTask == null) {
            this.currentTask = this.generator.generate();

            this.tasksGiven++;
        }

        return this.currentTask;
    }

    /**
     * Предоставить ответ ученика. Если результат {@link Result#INCORRECT_INPUT}, то счетчик неправильных
     * ответов не увеличивается, а {@link #nextTask()} в следующий раз вернет тот же самый объект {@link Task}.
     */
    public Result provideAnswer(String answer) throws NoTaskException {
        if (this.currentTask == null) {
            throw new NoTaskException("there is no task given at the moment");
        }

        Result result = this.currentTask.validate(answer);

        switch (result) {
            case OK -> {
                this.correctAnswersNumber++;
                this.currentTask = null;
            }
            case WRONG -> {
                this.wrongAnswerNumber++;
                this.currentTask = null;
            }
            case INCORRECT_INPUT -> {
                this.incorrectInputNumber++;
            }
        }

        return result;
    }

    /**
     * @return завершен ли тест
     */
    public boolean isFinished() {
        return this.tasksGiven == this.taskCount;
    }

    /**
     * @return количество правильных ответов
     */
    public int getCorrectAnswerNumber() {
        return this.correctAnswersNumber;
    }

    /**
     * @return количество неправильных ответов
     */
    public int getWrongAnswerNumber() {
        return this.wrongAnswerNumber;
    }

    /**
     * @return количество раз, когда был предоставлен неправильный ввод
     */
    public int getIncorrectInputNumber() {
        return this.incorrectInputNumber;
    }

    /**
     * @return оценка, которая является отношением количества правильных ответов к количеству всех вопросов.
     *         Оценка выставляется только в конце!
     */
    public double getMark() {
        if (!this.isFinished()) {
            throw new UnfinishedQuizException("cannot get result yet");
        }

        if (getCorrectAnswerNumber() + getWrongAnswerNumber() == 0) {
            return 1;
        }
        return ((double) getCorrectAnswerNumber()) / (getCorrectAnswerNumber() + getWrongAnswerNumber());
    }
}
