package by.mmaxemm.quizer;

import by.mmaxemm.quizer.exceptions.QuizNotFinishedException;
import by.mmaxemm.quizer.exceptions.TaskGenerationException;

import java.util.Map;

public class Quiz {

    int taskCount;
    int correctAnswerNumber;
    int wrongAnswerNumber;
    int incorrectInputNumber;

    TaskGenerator generator;
    boolean isIncorrectInput;
    Task currentTask;

    Quiz(TaskGenerator generator, int taskCount) {
        this.generator = generator;
        this.taskCount = taskCount;
        this.correctAnswerNumber = 0;
        isIncorrectInput = false;
    }

    Task nextTask() {
        if(isFinished()) {
            return null;
        }
        try {
            if(!isIncorrectInput || currentTask == null) {
                this.currentTask = generator.generate();
            }
        } catch (TaskGenerationException e) {
            System.out.println(e.getMessage());
        }
        return this.currentTask;
    }

    Result provideAnswer(String answer) {
        Result result = currentTask.validate(answer);
        if(result == Result.OK) {
            isIncorrectInput = false;
            correctAnswerNumber++;
        } else if(result == Result.WRONG) {
            isIncorrectInput = false;
            wrongAnswerNumber++;
        } else {
            isIncorrectInput = true;
            incorrectInputNumber++;
        }
        return result;
    }

    boolean isFinished() {
        return correctAnswerNumber + wrongAnswerNumber == taskCount;
    }

    int getCorrectAnswerNumber() {
        return correctAnswerNumber;
    }

    int getWrongAnswerNumber() {
        return taskCount - correctAnswerNumber;
    }

    int getIncorrectInputNumber() {
        return incorrectInputNumber;
    }

    double getMark() throws QuizNotFinishedException {
        if(!isFinished()) {
            throw new QuizNotFinishedException("Quiz isn't finished yet!");
        }
        return (double) correctAnswerNumber / taskCount;
    }
}
