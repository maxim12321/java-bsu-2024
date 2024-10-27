package by.pkasila.quizer;

import by.pkasila.quizer.exceptions.QuizException;

/**
 * Class, который описывает один тест
 */
public class Quiz {
    private final TaskGenerator generator;
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
    Quiz(TaskGenerator generator, int taskCount) {
        this.generator = generator;
        this.taskCount = taskCount;
    }

    /**
     * @return задание, повторный вызов вернет слелующее
     * @see Task
     */
    Task nextTask() throws QuizException {
        if (this.isFinished()) {
            throw new QuizException("the quiz is already finished");
        }

        this.currentTask = this.generator.generate();

        this.tasksGiven++;

        return this.currentTask;
    }

    /**
     * Предоставить ответ ученика. Если результат {@link Result#INCORRECT_INPUT}, то счетчик неправильных
     * ответов не увеличивается, а {@link #nextTask()} в следующий раз вернет тот же самый объект {@link Task}.
     */
    Result provideAnswer(String answer) throws QuizException {
        if (this.currentTask == null) {
            throw new QuizException("there is no task given at the moment");
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
    boolean isFinished() {
        return this.tasksGiven == this.taskCount;
    }

    /**
     * @return количество правильных ответов
     */
    int getCorrectAnswerNumber() {
        return this.correctAnswersNumber;
    }

    /**
     * @return количество неправильных ответов
     */
    int getWrongAnswerNumber() {
        return this.wrongAnswerNumber;
    }

    /**
     * @return количество раз, когда был предоставлен неправильный ввод
     */
    int getIncorrectInputNumber() {
        return this.incorrectInputNumber;
    }

    /**
     * @return оценка, которая является отношением количества правильных ответов к количеству всех вопросов.
     *         Оценка выставляется только в конце!
     */
    double getMark() {
        if (!this.isFinished()) {
            throw new QuizException("the quiz is not finished");
        }

        return ((double) this.correctAnswersNumber) / this.taskCount;
    }
}
