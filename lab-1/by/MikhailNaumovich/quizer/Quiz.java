package by.MikhailNaumovich.quizer; //fix

import by.MikhailNaumovich.quizer.exceptions.InvalidGeneratorException;
import by.MikhailNaumovich.quizer.exceptions.InvalidArgumentException;
import by.MikhailNaumovich.quizer.exceptions.QuizFinishedException;
import by.MikhailNaumovich.quizer.exceptions.QuizNotFinishedException;
/**
 * Class, который описывает один тест
 */

class Quiz {
    /**
     * @param generator генератор заданий
     * @param taskCount количество заданий в тесте
     */
    Quiz(TaskGenerator<? extends Task> generator, int taskCount) { 
        if (generator == null) {
            throw new InvalidGeneratorException("generator mustn't be null");
        }
        if (taskCount <= 0) {
            throw new InvalidArgumentException("taskCount must be positive");
        }
        this.generator = generator;
        this.taskCount = taskCount;
    }
    
    /**
     * @return задание, повторный вызов вернет слелующее
     * @see Task
     */
    Task nextTask() throws InvalidGeneratorException{
        if (isFinished()) {
            throw new QuizFinishedException("Quiz is finished");
        }
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
            throw new InvalidGeneratorException("currentTask mustn't be null");
        }
        if (isFinished()) {
            throw new QuizFinishedException("Quiz is finished");
        }
        Result resultCurrent = currentTask.validate(answer);
        switch (resultCurrent) {
            case OK:
                correctAnswerNumber++;
                currentTaskNumber++;
                currentTask = generator.generate();
                break;
            case WRONG:
                wrongAnswerNumber++;
                currentTaskNumber++;
                currentTask = generator.generate();
                break;
            case INCORRECT_INPUT:
                incorrectInputNumber++;
                break;
        }
        return resultCurrent;
    }
    
    /**
     * @return завершен ли тест
     */
    boolean isFinished() {
        return currentTaskNumber == taskCount;
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
    double getMark() {
        if (!isFinished()) {
            throw new QuizNotFinishedException("Quiz is not finished");
        }
        double mark = 10 * ((double) correctAnswerNumber) / ((double) taskCount);
        double eps = 0.001;
        return Math.round(mark / eps) * eps;
    }

    private final TaskGenerator<? extends Task> generator;
    private final int taskCount;
    private Task currentTask = null;
    private int currentTaskNumber = 0;
    private int correctAnswerNumber = 0;
    private int wrongAnswerNumber = 0;
    private int incorrectInputNumber = 0;
}
