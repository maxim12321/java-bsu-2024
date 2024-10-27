package by.MikhailNaumovich.quizer;


import by.MikhailNaumovich.quizer.exceptions.InvalidArgumentException;
import by.MikhailNaumovich.quizer.exceptions.InvalidGeneratorException;
import by.MikhailNaumovich.quizer.exceptions.QuizFinishedException;
import by.MikhailNaumovich.quizer.exceptions.QuizNotFinishedException;


/**
 * Class, который описывает один тест
 */

class Quiz {
    
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
    
    Task nextTask() throws InvalidGeneratorException{
        if (isFinished()) {
            throw new QuizFinishedException("Quiz is finished");
        }
        if (validityChecker) {
            validityChecker = false;
            currentTask = generator.generate();
        }
        return currentTask;
    }
    
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
                validityChecker = true;
            case WRONG:
                wrongAnswerNumber++;
                validityChecker = true;
            case INCORRECT_INPUT:
                incorrectInputNumber++;
        }
        currentTaskNumber += validityChecker ? 1 : 0;
        return resultCurrent;
    }
    
    boolean isFinished() {
        return currentTaskNumber == taskCount;
    }

    int getTasksAmount() {
        return taskCount;
    }
    
    int getCorrectAnswerNumber() {
        return correctAnswerNumber;
    }
    
    int getWrongAnswerNumber() {
        return wrongAnswerNumber;
    }
    
    int getIncorrectInputNumber() {
        return incorrectInputNumber;
    }
    
    double getMark() {
        if (!isFinished()) {
            throw new QuizNotFinishedException("Quiz is not finished");
        }
        if (getCorrectAnswerNumber() + getWrongAnswerNumber() == 0) return 1;
        double mark = 10 * ((double) correctAnswerNumber) / ((double) taskCount);
        double eps = 0.001;
        return Math.round(mark / eps) * eps;
    }

    private final TaskGenerator<? extends Task> generator;
    private final int taskCount;
    private Task currentTask;
    private boolean validityChecker = true;
    private int correctAnswerNumber = 0;
    private int wrongAnswerNumber = 0;
    private int incorrectInputNumber = 0;
    private int currentTaskNumber = 0;
} 