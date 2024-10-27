package by.KuryanovichNikita.quizer;

import by.KuryanovichNikita.quizer.exceptions.NotValidGenerator;
import by.KuryanovichNikita.quizer.exceptions.QuizFinishedException;
import by.KuryanovichNikita.quizer.exceptions.QuizNotFinishedException;
import by.KuryanovichNikita.quizer.generators.PoolTaskGenerator;
import by.KuryanovichNikita.quizer.generators.TaskGenerator;
import by.KuryanovichNikita.quizer.tasks.Task;

import java.util.Map;

/**
 * Class, который описывает один тест
 */
class Quiz {

    private final TaskGenerator generator;
    private Task currTask;
    private boolean isLastCorrInput;
    private int taskLeft;
    private final int[] answersCounters = {0, 0, 0};

    /**
     * @param generator генератор заданий
     * @param taskCount количество заданий в тесте
     */
    Quiz(TaskGenerator generator, int taskCount) {
        this.generator = generator;
        this.taskLeft = taskCount;
        isLastCorrInput = true;
    }

    /**
     * @return задание, повторный вызов вернет следующее
     * @see Task
     */
    Task nextTask() throws NotValidGenerator {
        if(isFinished())
            throw new QuizFinishedException("Quiz Finished, you can not ask for next task");
        if(isLastCorrInput){
            isLastCorrInput = false;
            currTask = generator.generate();
        }
        return currTask;
    }

    /**
     * Предоставить ответ ученика. Если результат {@link Result#INCORRECT_INPUT}, то счетчик неправильных
     * ответов не увеличивается, а {@link #nextTask()} в следующий раз вернет тот же самый объект {@link Task}.
     */
    Result provideAnswer(String answer) throws QuizFinishedException {
        if (isFinished()) {
            throw new QuizFinishedException("Quiz is finished. You can't provide answer.");
        }
        Result result = currTask.validate(answer);
        isLastCorrInput = (result != Result.INCORRECT_INPUT);
        taskLeft -= isLastCorrInput ? 1 : 0;
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
     *         Оценка выставляется только в конце!
     */
    double getMark() throws QuizNotFinishedException {

            if (!isFinished()) {
                throw new QuizNotFinishedException("Quiz is not finished, you can't get the result yet.");
            }
            if (getCorrectAnswerNumber() + getWrongAnswerNumber() == 0) {
                return 0;
            }
            return ((double) getCorrectAnswerNumber()) / (getCorrectAnswerNumber() + getWrongAnswerNumber());
    }
}

