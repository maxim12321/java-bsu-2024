package by.kirsiv40.quizer.Main;

import java.util.Map;
import java.util.Scanner;

import by.kirsiv40.quizer.Main.Exceptions.QuizIsNotFinished;
import by.kirsiv40.quizer.Tasks.Task;

public class Main {
    public static void main(String[] args) throws QuizIsNotFinished {
        final Map<String, Quiz> a = Quiz.getQuizMap();
        System.out.println("Тесты в системе:");
        for (var el : a.keySet()) {
            System.out.println(el);
        }
        Scanner in = new Scanner(System.in);
        System.out.println("Введите название теста: ");
        String input = in.nextLine();
        Quiz test = a.get(input);
        while (test == null) {
            System.out.println("Такого теста нет в списке. Введите название теста: ");
            input = in.nextLine();
            test = a.get(input);
        }
        while (!test.isFinished()) {
            Task cur = test.nextTask();
            System.out.println(cur.getText());
            test.provideAnswer(in.nextLine());
        }
        System.out.println("Ваш результат: " + test.getMark());
        System.out.println("Правильных ответов: " + test.getCorrectAnswerNumber());
        System.out.println("Неправильных ответов: " + test.getWrongAnswerNumber());
        in.close();
    }
}






