package by.SanchukS.quizer;

import by.SanchukS.quizer.exceptions.NullArgumentException;
import by.SanchukS.quizer.exceptions.QuizNotFinishedException;
import by.SanchukS.quizer.exceptions.SeveralAnswersException;

/**
 * Class, который описывает один тест
 */
public class Quiz {
    private final int taskCount;
    private final TaskGenerator generator;

    private int correctAnswersNumber = 0;
    private int wrongAnswersNumber = 0;
    private int incorrectInputNumber = 0;

    private Task currentTask;
    private Result currentResult;

    /**
     * @param generator генератор заданий
     * @param taskCount количество заданий в тесте
     */
    Quiz(TaskGenerator generator, int taskCount) {
        if (generator == null) throw new NullArgumentException("generator");
        if (taskCount <= 0) throw new IllegalArgumentException("Invalid task count");
        this.generator = generator;
        this.taskCount = taskCount;
    }

    /**
     * @return задание, повторный вызов вернет слелующее
     * @see Task
     */
    Task nextTask() {
        if (currentResult == Result.OK || currentResult == Result.WRONG) {
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
            throw new SeveralAnswersException();
        }

        currentResult = currentTask.validate(answer);
        switch (currentResult) {
            case OK -> correctAnswersNumber++;
            case WRONG -> wrongAnswersNumber++;
            case INCORRECT_INPUT -> incorrectInputNumber++;
            default -> throw new IllegalArgumentException("Unsupported value: " + currentResult);
        }
        return currentResult;
    }

    /**
     * @return завершен ли тест
     */
    boolean isFinished() {
        return correctAnswersNumber + wrongAnswersNumber == taskCount;
    }

    /**
     * @return количество правильных ответов
     */
    int getCorrectAnswerNumber() {
        return correctAnswersNumber;
    }

    /**
     * @return количество неправильных ответов
     */
    int getWrongAnswerNumber() {
        return wrongAnswersNumber;
    }

    /**
     * @return количество раз, когда был предоставлен неправильный ввод
     */
    int getIncorrectInputNumber() {
        return incorrectInputNumber;
    }

    /**
     * @return оценка, которая является отношением количества правильных ответов к количеству всех вопросов.
     *         Оценка выставляется только в конце!
     */
    double getMark() {
        if (!isFinished()) throw new QuizNotFinishedException();
        return (double) correctAnswersNumber / taskCount;
    }
}
