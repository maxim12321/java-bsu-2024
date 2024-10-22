package by.VadzimKamianetski.Quizer;

import by.VadzimKamianetski.Quizer.TaskGenerators.TaskGenerator;
import by.VadzimKamianetski.Quizer.Tasks.Task;
import by.VadzimKamianetski.Quizer.exceptions.PoolGeneratorSamplesException;
import by.VadzimKamianetski.Quizer.exceptions.QuizNotFinishedException;

/**
 * Class, который описывает один тест
 */
public class Quiz {

    private int correctCount = 0;
    private int incorrectCount = 0;
    private TaskGenerator<? extends Task> generator;
    private Task task;
    private int taskCount;
    private int incorrectInputCount = 0;

    private boolean lastInput = true;
    
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
     * @throws PoolGeneratorSamplesException 
     * @see Task
     */
    Task nextTask() throws PoolGeneratorSamplesException {
        if (lastInput) {
            task = generator.generate();
            taskCount--;
        }
        return task;
    }
    
    /**
     * Предоставить ответ ученика. Если результат {@link Result#INCORRECT_INPUT}, то счетчик неправильных 
     * ответов не увеличивается, а {@link #nextTask()} в следующий раз вернет тот же самый объект {@link Task}.
     */
    Result provideAnswer(String answer) {
        Result result = task.validate(answer);
        lastInput = true;
        switch (result) {
            case Result.OK:
                correctCount++;
                break;
            case Result.WRONG:
                incorrectCount++;
                break;

            case Result.INCORRECT_INPUT:
                incorrectInputCount++;
                lastInput = false;
                break;    
        }
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
     * @throws QuizNotFinishedException 
     */
    double getMark() throws QuizNotFinishedException {
        if (taskCount != 0) {
            throw new QuizNotFinishedException("Quiz isn't finished");
        }
        return (double) (5 * correctCount / (incorrectCount + correctCount));
    }
}