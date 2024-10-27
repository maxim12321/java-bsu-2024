package by.maxsavitsky.quizer;

import by.maxsavitsky.quizer.exceptions.QuizNotFinishedException;
import by.maxsavitsky.quizer.generators.TaskGenerator;
import by.maxsavitsky.quizer.tasks.Task;

/**
 * Class, который описывает один тест
 */
public class Quiz {
    private final TaskGenerator<? extends Task> generator;
    private final int taskCount;
    private Task currentTask;

    private int correctAnswerNumber = 0;
    private int wrongAnswerNumber = 0;
    private int incorrectInputNumber = 0;

    /**
     * @param generator генератор заданий
     * @param taskCount количество заданий в тесте
     */
    public Quiz(TaskGenerator<? extends Task> generator, int taskCount) {
        this.generator = generator;
        this.taskCount = taskCount;
        currentTask = generator.generate();
    }

    /**
     * @return задание, повторный вызов вернет слелующее
     * @see Task
     */
    Task nextTask() {
        return currentTask;
    }

    /**
     * Предоставить ответ ученика. Если результат {@link Result#INCORRECT_INPUT}, то счетчик неправильных
     * ответов не увеличивается, а {@link #nextTask()} в следующий раз вернет тот же самый объект {@link Task}.
     */
    Result provideAnswer(String answer) {
        Result res = currentTask.validate(answer);
        if (res == Result.OK) {
            currentTask = generator.generate();
            correctAnswerNumber++;
        } else if (res == Result.WRONG) {
            currentTask = generator.generate();
            wrongAnswerNumber++;
        } else {
            incorrectInputNumber++;
        }
        return res;
    }

    /**
     * @return завершен ли тест
     */
    boolean isFinished() {
        return correctAnswerNumber + wrongAnswerNumber == taskCount;
    }

    /**
     * @return количество правильных ответов
     */
    int getCorrectAnswerNumber() {
        return correctAnswerNumber;
    }

    /**
     * @return количество неправильных ответов
     */
    int getWrongAnswerNumber() {
        return wrongAnswerNumber;
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
    double getMark() throws QuizNotFinishedException {
        if (!isFinished()) {
            throw new QuizNotFinishedException();
        }
        return (double) correctAnswerNumber / taskCount;
    }
}
