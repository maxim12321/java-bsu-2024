package by.DmitryAntashkevich.quizer;

import by.DmitryAntashkevich.quizer.exceptions.QuizNotFinishedException;

/**
 * Class, который описывает один тест
 */
public class Quiz {
    /**
     * @param generator генератор заданий
     * @param taskCount количество заданий в тесте
     */
    Quiz(TaskGenerator<? extends Task> generator, int taskCount) {
        this.generator = generator;
        this.taskCount = taskCount;
    }

    /**
     * @return задание, повторный вызов вернет следующее
     * @see Task
     */
    Task nextTask() {
        if (currentTask == null) {
            currentTask = generator.generate();
        }
        return currentTask;
    }

    /**
     * Предоставить ответ ученика. Если результат {@link Result#INCORRECT_INPUT}, то счетчик неправильных
     * ответов не увеличивается, а {@link #nextTask()} в следующий раз вернет тот же самый объект {@link Task}.
     */
    Result provideAnswer(String answer) {
        if (currentTask == null) {
            throw new RuntimeException("Attempt to answer a non-existent question");
        }
        Result result = currentTask.validate(answer);
        switch (result) {
            case OK -> correctAnswerCount++;
            case WRONG -> wrongAnswerCount++;
            case INCORRECT_INPUT -> incorrectAnswerCount++;
        }
        if (!result.equals(Result.INCORRECT_INPUT)) {
            currentTask = null;
            completedTaskCount++;
        }
        return result;
    }

    /**
     * @return завершен ли тест
     */
    boolean isFinished() {
        return completedTaskCount == taskCount;
    }

    /**
     * @return количество правильных ответов
     */
    public int getCorrectAnswerNumber() {
        return correctAnswerCount;
    }

    /**
     * @return количество неправильных ответов
     */
    public int getWrongAnswerNumber() {
        return wrongAnswerCount;
    }

    /**
     * @return количество раз, когда был предоставлен неправильный ввод
     */
    public int getIncorrectInputNumber() {
        return incorrectAnswerCount;
    }

    /**
     * @return оценка, которая является отношением количества правильных ответов к количеству всех вопросов.
     * Оценка выставляется только в конце!
     */
    public double getMark() {
        if (!isFinished()) {
            throw new QuizNotFinishedException("Quiz is not finished yet");
        }
        return (double) correctAnswerCount / (double) taskCount;
    }

    private final TaskGenerator<? extends Task> generator;
    private final int taskCount;
    private int completedTaskCount = 0;
    private int correctAnswerCount = 0;
    private int wrongAnswerCount = 0;
    private int incorrectAnswerCount = 0;
    private Task currentTask = null;
}