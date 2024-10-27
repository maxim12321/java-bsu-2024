package by.AlbertRadoshko.quizer;

import java.util.ArrayList;

/**
 * Class, который описывает один тест
 */
public class Quiz {
    /**
     * @param generator генератор заданий
     * @param taskCount количество заданий в тесте
     */
    Quiz(TaskGenerator generator, int taskCount) {
        tasks = new ArrayList<>();
        for (int i = 0; i < taskCount; i++) {
            tasks.add(generator.generate());
        }
    }

    /**
     * @return задание, повторный вызов вернет слелующее
     * @see Task
     */
    Task nextTask() {
        return tasks.get(taskPtr);
    }

    /**
     * Предоставить ответ ученика. Если результат {@link Result#INCORRECT_INPUT}, то счетчик неправильных
     * ответов не увеличивается, а {@link #nextTask()} в следующий раз вернет тот же самый объект {@link Task}.
     */
    Result provideAnswer(String answer) {
        var validationResult = tasks.get(taskPtr).validate(answer);
        if (validationResult != Result.INCORRECT_INPUT) {
            taskPtr++;
        }
        return validationResult;
    }

    /**
     * @return завершен ли тест
     */
    boolean isFinished() {
        return taskPtr == tasks.size();
    }

    /**
     * @return количество правильных ответов
     */
    int getCorrectAnswerNumber() {
        return tasks.size() - incorrectAnsCount;
    }

    /**
     * @return количество неправильных ответов
     */
    int getWrongAnswerNumber() {
        return incorrectAnsCount;
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
    double getMark() {
        return (double) getCorrectAnswerNumber() / (double) tasks.size();
    }

    Result lastAns = Result.INCORRECT_INPUT;
    ArrayList<Task> tasks;
    int taskPtr = 0;
    int incorrectAnsCount = 0;
    int incorrectInputCount = 0;
}