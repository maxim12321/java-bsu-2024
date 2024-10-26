package by.chewy_pegasus.quizer;

import by.chewy_pegasus.quizer.exceptions.QuizNotFinishedException;

/**
 * Class, который описывает один тест
 */
class Quiz {
    /**
     * @param generator генератор заданий
     * @param taskCount количество заданий в тесте
     */
    private final TaskGenerator generator;
    private int taskCount;
    private int correctCount = 0;
    private int incorrectCount = 0;
    private int incorrectInputCount = 0;
    private Task curTask = null;

    Quiz(TaskGenerator generator, int taskCount) {
        this.generator = generator;
        this.taskCount = taskCount;
    }

    /**
     * @return задание, повторный вызов вернет следующее
     * @see Task
     */
    Task nextTask() {
        // ...
        Task task = (Task) this.generator.generate();
        this.curTask = task;
        return task;
    }

    /**
     * Предоставить ответ ученика. Если результат {@link Result#INCORRECT_INPUT}, то счетчик неправильных
     * ответов не увеличивается, а {@link #nextTask()} в следующий раз вернет тот же самый объект {@link Task}.
     */
    Result provideAnswer(String answer) {
        Result result = this.curTask.validate(answer);
        if (result.equals(Result.OK)) {
            ++correctCount;
        } else if (result.equals(Result.WRONG)) {
            ++incorrectCount;
        } else {
            ++incorrectInputCount;
        }
        --this.taskCount;
        return result;
    }

    /**
     * @return завершен ли тест
     */
    boolean isFinished() {
        return (this.taskCount == 0);
    }

    /**
     * @return количество правильных ответов
     */
    int getCorrectAnswerNumber() {
        return correctCount;
    }

    /**
     * @return количество неправильных ответов
     */
    int getWrongAnswerNumber() {
        return incorrectCount;
    }

    /**
     * @return количество раз, когда был предоставлен неправильный ввод
     */
    int getIncorrectInputNumber() {
        return incorrectInputCount;
    }

    /**
     * @return оценка, которая является отношением количества правильных ответов к количеству всех вопросов.
     *         Оценка выставляется только в конце!
     */
    double getMark() throws QuizNotFinishedException {
        if (this.taskCount > 0) {
            throw new QuizNotFinishedException("try getMark before finish");
        }
        return Math.floor((double) correctCount / (correctCount + incorrectCount) * 10. * 100) / 100;
    }
}