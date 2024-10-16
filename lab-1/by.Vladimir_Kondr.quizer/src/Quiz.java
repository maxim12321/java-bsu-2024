import core.Result;
import core.Task;
import core.TaskGenerator;
import exceptions.QuizFinishedException;
import exceptions.QuizNotFinishedException;

import java.lang.reflect.Array;

/**
 * Class, который описывает один тест
 */
public class Quiz {
    private int taskCount;
    private final TaskGenerator<? extends Task> generator;
    private boolean isLastAnswerValid = true;
    private Task task;
    private int[] answersCounters = {0, 0, 0};
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
            throw new QuizFinishedException("Quiz finished. You can't ask for next task.");
        }
        if (isLastAnswerValid) {
            task = generator.generate();
        }
        return task;
    }

    /**
     * Предоставить ответ ученика. Если результат {@link Result#INCORRECT_INPUT}, то счетчик неправильных
     * ответов не увеличивается, а {@link #nextTask()} в следующий раз вернет тот же самый объект {@link Task}.
     */
    Result provideAnswer(String answer) {
        if (isFinished()) {
            throw new QuizFinishedException("Quiz is finished. You can't provide answer.");
        }
        Result result = task.validate(answer);
        isLastAnswerValid = (result == Result.INCORRECT_INPUT);
        taskCount -= isLastAnswerValid ? 1 : 0;
        answersCounters[result.ordinal()]++;
        return result;
    }

    /**
     * @return завершен ли тест
     */
    boolean isFinished() {
        return taskCount == 0;
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
     *         Оценка выставляется только в конце!
     */
    double getMark() {
        if (!isFinished()) {
            throw new QuizNotFinishedException("Quiz is not finished, you can't get the result yet.");
        }
        if (getCorrectAnswerNumber() + getWrongAnswerNumber() == 0) {
            return 1;
        }
        return ((double) getCorrectAnswerNumber()) / (getCorrectAnswerNumber() + getWrongAnswerNumber());
    }
}