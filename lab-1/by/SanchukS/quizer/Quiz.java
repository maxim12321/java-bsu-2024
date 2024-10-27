package by.SanchukS.quizer;

/**
 * Class, который описывает один тест
 */
public class Quiz {
    private final int taskCount;
    private final TaskGenerator generator;

    private int correctAnswers = 0;
    private int wrongAnswers = 0;
    private int incorrectInput = 0;

    private Task currentTask;
    private Result currentResult;

    /**
     * @param generator генератор заданий
     * @param taskCount количество заданий в тесте
     */
    Quiz(TaskGenerator generator, int taskCount) {
        if (generator == null) throw new IllegalArgumentException("Null argument");
        if (taskCount <= 0) throw new IllegalArgumentException("Invalid task count");
        this.generator = generator;
        this.taskCount = taskCount;
    }

    /**
     * @return задание, повторный вызов вернет слелующее
     * @see Task
     */
    Task nextTask() {
        if (currentResult != Result.INCORRECT_INPUT) {
            currentTask = generator.generate();
            currentResult = null;
        }
        return currentTask;
    }

    /**
     * Предоставить ответ ученика. Если результат {@link Result#INCORRECT_INPUT}, то счетчик неправильных
     * ответов не увеличивается, а {@link #nextTask()} в следующий раз вернет тот же самый объект {@link Task}.
     */
    Result provideAnswer(String answer) {
        if (currentResult == Result.OK || currentResult == Result.WRONG) {
            throw new IllegalStateException("Several answers to a Task");
        }

        currentResult = currentTask.validate(answer);
        switch (currentResult) {
            case OK -> correctAnswers++;
            case WRONG -> wrongAnswers++;
            case INCORRECT_INPUT -> incorrectInput++;
            default -> throw new IllegalStateException("Unexpected value: " + currentResult);
        }
        return currentResult;
    }

    /**
     * @return завершен ли тест
     */
    boolean isFinished() {
        return correctAnswers + wrongAnswers == taskCount;
    }

    /**
     * @return количество правильных ответов
     */
    int getCorrectAnswerNumber() {
        return correctAnswers;
    }

    /**
     * @return количество неправильных ответов
     */
    int getWrongAnswerNumber() {
        return wrongAnswers;
    }

    /**
     * @return количество раз, когда был предоставлен неправильный ввод
     */
    int getIncorrectInputNumber() {
        return incorrectInput;
    }

    /**
     * @return оценка, которая является отношением количества правильных ответов к количеству всех вопросов.
     *         Оценка выставляется только в конце!
     */
    double getMark() {
        if (!isFinished()) throw new IllegalStateException("Quiz is not finished.");
        return (double) correctAnswers / taskCount;
    }
}
