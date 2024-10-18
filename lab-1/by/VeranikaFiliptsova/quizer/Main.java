package by.VeranikaFiliptsova.quizer;

import by.VeranikaFiliptsova.quizer.generators.math.EquationMathTaskGenerator;
import by.VeranikaFiliptsova.quizer.generators.math.ExpressionMathTaskGenerator;
import by.VeranikaFiliptsova.quizer.tasks.math.EquationMathTask;
import by.VeranikaFiliptsova.quizer.tasks.math.ExpressionMathTask;

import java.util.Map;
import java.util.Scanner;

public class Main {
    /*
    static Map<String, Quiz> getQuizMap() {
        //TODO генераторами нагенерить
        return Map.of();
    }

    public static void main(String[] args) {
        Map<String, Quiz> quizzes = getQuizMap();
        Scanner console = new Scanner(System.in);
        String name;
        do {
            System.out.println("Введите название теста...");
            name = console.next();
        } while (!quizzes.containsKey(name));
        Quiz currentQuiz = quizzes.get(name);
        currentQuiz.execute();
        System.out.println(currentQuiz.getMark()); //вывести результат

    }
    */
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        ExpressionMathTaskGenerator taskgen = new ExpressionMathTaskGenerator(2, 20, false, true, true, true);
        for (int i = 0; i < 3; i ++) {
            ExpressionMathTask task = taskgen.generate();
            System.out.println(task.getText());
            String answer = console.next();
            Result currentResult = task.validate(answer);
            if (currentResult == Result.OK) {
                System.out.println("Верно");
            } else if (currentResult == Result.WRONG) {
                System.out.println("Неверно");
            } else {
                System.out.println("Неверный ввод");
            }
        }

        EquationMathTaskGenerator taskgen2 = new EquationMathTaskGenerator(-2, 2, false, true, true, true);
        for (int i = 0; i < 10; i ++) {
            EquationMathTask task = taskgen2.generate();
            System.out.println(task.getText());
            String answer = console.next();
            Result currentResult = task.validate(answer);
            if (currentResult == Result.OK) {
                System.out.println("Верно");
            } else if (currentResult == Result.WRONG) {
                System.out.println("Неверно");
            } else {
                System.out.println("Неверный ввод");
            }
        }
    }
}