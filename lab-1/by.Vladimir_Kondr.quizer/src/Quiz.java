import core.GeneratorCreator;
import core.Result;
import core.Task;
import core.TaskGenerator;
import exceptions.QuizFinishedException;
import exceptions.QuizNotFinishedException;
import generators.PoolTaskGenerator;

import java.util.Map;

/**
 * Class, который описывает один тест
 */
public class Quiz extends GeneratorCreator {
    private int taskCount;
    private TaskGenerator<? extends Task> generator;
    private final Class<? extends TaskGenerator<? extends Task>> generatorClass;
    private final Map<String, Object> argsMap;
    private boolean isLastAnswerValid = true;
    private Task task;
    private final int[] answersCounters = {0, 0, 0};
    /**
     * @param generator генератор заданий
     * @param taskCount количество заданий в тесте
     */
    Quiz(TaskGenerator<? extends Task> generator, int taskCount) {
        this.generator = generator;
        this.taskCount = taskCount;
        this.generatorClass = null;
        this.argsMap = null;
        if (generator instanceof PoolTaskGenerator && taskCount > ((PoolTaskGenerator) generator).getSize() && !((PoolTaskGenerator) generator).getAllowDuplicates()) {
            throw new IllegalArgumentException("Quiz is built on PoolTaskGenerator but demands from it more tasks than generator can produce");
        }
    }

    /**
     * @param generatorClass класс генератора заданий
     * @param taskCount количество заданий в тесте
     * @param argsMap словарь с аргументами для конструктора генератора
     */
    Quiz(Class<? extends TaskGenerator<? extends Task>> generatorClass, int taskCount, Map<String, Object> argsMap) {
        this.generatorClass = generatorClass;
        this.taskCount = taskCount;
        this.argsMap = argsMap;
        this.generator = null;
    }

    /**
     * @return задание, повторный вызов вернет слелующее
     * @see Task
     */
    Task nextTask() {
        if (isFinished()) {
            throw new QuizFinishedException("Quiz finished. You can't ask for next task.");
        }
        if (generator == null) {
            generator = createGenerator(generatorClass, argsMap);
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
        isLastAnswerValid = (result != Result.INCORRECT_INPUT);
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