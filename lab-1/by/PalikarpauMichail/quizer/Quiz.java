package by.PalikarpauMichail.quizer;

import by.PalikarpauMichail.quizer.exceptions.OutOfTasksException;
import by.PalikarpauMichail.quizer.exceptions.QuizNotFinishedException;

/**
 * Class, который описывает один тест
 */
class Quiz {
    /**
     * @param generator генератор заданий
     * @param taskCount количество заданий в тесте
     */
    Quiz(TaskGenerator<?> generator, int taskCount) { 
        this.generator = generator;
        this.cashedTask = null;
        this.taskCount = taskCount;
        this.currentTaskIndex = 0;
        this.correctAnswerCount = 0;
        this.wrongAnswerCount = 0;
        this.incorrectInputCount = 0;
    }
    
    /**
     * @return задание, повторный вызов вернет слелующее
     * @see Task
     */
    Task nextTask() {
        if (cashedTask != null) {
            return cashedTask;
        }
        if (currentTaskIndex < taskCount) {
            cashedTask = generator.generate();
            return cashedTask;
        } else {
            throw new OutOfTasksException();
        }
    }
    
    /**
     * Предоставить ответ ученика. Если результат {@link Result#INCORRECT_INPUT}, то счетчик неправильных 
     * ответов не увеличивается, а {@link #nextTask()} в следующий раз вернет тот же самый объект {@link Task}.
     */
    Result provideAnswer(String answer) {
        Result result = cashedTask.validate(answer);
        switch(result) {
            case OK -> {
                System.out.println("Верный ответ");
                currentTaskIndex++;
                correctAnswerCount++;
                cashedTask = null;
            }
            case WRONG -> {
                System.out.println("Неверный ответ");
                currentTaskIndex++;
                incorrectInputCount++;
                cashedTask = null;
            }
            case INCORRECT_INPUT -> {
                System.out.println("Неправильный ввод. Попробуйте ещё раз");
                incorrectInputCount++;
            }
        }
        return result;
    }
    
    /**
     * @return завершен ли тест
     */
    boolean isFinished() {
        return currentTaskIndex == taskCount;
    }
    
    /**
     * @return количество правильных ответов
     */
    int getCorrectAnswerNumber() {
        return correctAnswerCount;
    }
    
    /**
     * @return количество неправильных ответов
     */
    int getWrongAnswerNumber() {
        return wrongAnswerCount;
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
    double getMark() throws QuizNotFinishedException {
        if (currentTaskIndex != taskCount) {
            throw new QuizNotFinishedException();
        } else {
            if (taskCount == 0) {
                return 1;
            }
            return (double)correctAnswerCount / (double)taskCount;
        }
    }

    TaskGenerator<?> generator;
    Task cashedTask;
    int taskCount;
    int currentTaskIndex;
    int correctAnswerCount;
    int wrongAnswerCount;
    int incorrectInputCount;
}