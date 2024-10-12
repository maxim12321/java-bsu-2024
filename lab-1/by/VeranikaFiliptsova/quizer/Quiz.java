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
            counterOK++;
            counter--;
        } else if (currentResult == Result.WRONG) {
            counterWrong++;
            counter--;
        } else {
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
            return (double) counterOK / (counterOK + counterWrong);
        }
        return 1;
        //QuizNotFinishedException;
    }

    void execute() {
        Scanner console = new Scanner(System.in);
        while (!isFinished()) {
            System.out.println(currentTask.getText());
            String answer = console.next();
            Result res = provideAnswer(answer);
            switch (res) {
                case Result.OK -> System.out.println("Ваш ответ верен");
                case Result.WRONG -> System.out.println("Ваш ответ неверен");
                case Result.INCORRECT_INPUT -> System.out.println("Некорректные данные ввода");
            }
            if (res != Result.INCORRECT_INPUT) {
                currentTask = nextTask();
            }
        }
    }

}