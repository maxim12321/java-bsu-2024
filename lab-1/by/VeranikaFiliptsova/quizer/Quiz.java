package by.VeranikaFiliptsova.quizer;

import java.util.Scanner;

public class Quiz {
    int counter;
    int counterWrong;
    int counterOK;
    int counterIncInput;
    Task currentTask;
    TaskGenerator currentGenerator;

    //TO ASK мы должны в коллекции вопросы хранить или можно в hasnext просто просить генератор сгенерировать новый
    Quiz(TaskGenerator generator, int taskCount) {
        currentGenerator = generator;
        counter = taskCount;
    }


    Task nextTask() {
        return currentGenerator.generate();
    }


    Result provideAnswer(String answer) {
        Result currentResult = currentTask.validate(answer);
        if (currentResult == Result.OK) {
            System.out.println("Верно");
            counterOK++;
            counter--;
        } else if (currentResult == Result.WRONG) {
            System.out.println("Неверно");
            counterWrong++;
            counter--;
        } else {
            System.out.println("Неверный ввод");
            counterIncInput++;
        }
        return currentResult;
    }


    boolean isFinished() {
        return counter == 0;
    }


    int getCorrectAnswerNumber() {
        return counterOK;
    }


    int getWrongAnswerNumber() {
        return counterWrong;
    }


    int getIncorrectInputNumber() {
        return counterIncInput;
    }

    double getMark() {
        if (isFinished()) {
            return (double) counterOK /(counterOK + counterWrong);
        }
        //QuizNotFinishedException;
        return 1;
    }

    void execute() {
        Scanner console = new Scanner(System.in);
        while (!isFinished()) {
            System.out.print(currentTask.getText());
            String answer = console.next();
            Result res = provideAnswer(answer);
            if (res != Result.INCORRECT_INPUT) {
                currentTask = nextTask();
            }
        }
    }

}