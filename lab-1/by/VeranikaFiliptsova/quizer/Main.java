package by.VeranikaFiliptsova.quizer;

import java.util.Map;
import java.util.Scanner;

public class Main {
    static Map<String, Quiz> getQuizMap() {
        //здесь надо нагенерить
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
}