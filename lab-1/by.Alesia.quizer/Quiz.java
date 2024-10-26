package by.Alesia.quizer;

import by.Alesia.quizer.exceptions.EndOfTasks;
import by.Alesia.quizer.exceptions.NotTheWholeTest;

/**
 * Class, который описывает один тест
 */
public class Quiz {
    /**
     * @param generator генератор заданий
     * @param taskCount количество заданий в тесте
     */
    public Quiz(TaskGenerator generator, int taskCount) {
        this.taskCount = taskCount;
        this.generator = generator;
    }

    /**
     * @return задание, повторный вызов вернет следующее
     * @see Task
     */
    public Task nextTask() {
        if (isFinished()) {
            throw new EndOfTasks("Quiz finished. You can't ask for next task.");
        }
        if (prefCall) {
            prefCall = false;
            return task;
        }
        task = generator.generate();
        return task;
    }

    /**
     * Предоставить ответ ученика. Если результат {@link Result#INCORRECT_INPUT}, то счетчик неправильных
     * ответов не увеличивается, а {@link #nextTask()} в следующий раз вернет тот же самый объект {@link Task}.
     */
    public Result provideAnswer(String answer) {
        if (isFinished()) {
            throw new EndOfTasks("Quiz finished");
        }
        Result temp = task.validate(answer);
        if (temp == Result.INCORRECT_INPUT) {
            prefCall = true;
            return temp;
        }
        if (temp == Result.OK) {
            result[0]++;
        } else if(temp == Result.WRONG) {
            result[1]++;
        }
        taskCount--;
        prefCall = false;
        return temp;
    }

    /**
     * @return завершен ли тест
     */
    public boolean isFinished() {
        return taskCount == 0;
    }

    /**
     * @return количество правильных ответов
     */
    int getCorrectAnswerNumber() {
        return result[0];
    }

    /**
     * @return количество неправильных ответов
     */
    int getWrongAnswerNumber() {
        return result[1];
    }

    /**
     * @return количество раз, когда был предоставлен неправильный ввод
     */
    int getIncorrectInputNumber() {
        return result[2];
    }

    /**
     * @return оценка, которая является отношением количества правильных ответов к количеству всех вопросов.
     *         Оценка выставляется только в конце!
     */
    public double getMark() {
        if (!isFinished()) {
            throw new NotTheWholeTest("The rating cannot be known. The test was not completed.");
        }
        if (result[0] + result[1] + result[2] == 0) {
            return 1;
        }
        return 10 * ((double)result[0] / ((double)(result[0] + result[1] + result[2])));
    }


    private boolean prefCall = true;
    private final int[] result = {0, 0, 0};
    private Task task;
    private int taskCount;
    private final TaskGenerator generator;
}