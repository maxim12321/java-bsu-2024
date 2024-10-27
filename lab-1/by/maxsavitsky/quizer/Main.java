package by.maxsavitsky.quizer;

import by.maxsavitsky.quizer.exceptions.QuizNotFinishedException;
import by.maxsavitsky.quizer.generators.EquationTaskGenerator;
import by.maxsavitsky.quizer.generators.ExpressionTaskGenerator;
import by.maxsavitsky.quizer.generators.PoolTaskGenerator;
import by.maxsavitsky.quizer.tasks.Task;
import by.maxsavitsky.quizer.tasks.TextTask;
import by.maxsavitsky.quizer.tasks.math.MathTask;

import java.util.EnumSet;
import java.util.Map;
import java.util.Scanner;

public class Main {

    static Map<String, Quiz> getQuizMap() {
        return Map.of(
                "арифметика", new Quiz(new ExpressionTaskGenerator(0, 100, EnumSet.allOf(MathTask.Operation.class)), 5),
                "уравнения", new Quiz(new EquationTaskGenerator(0, 100, EnumSet.allOf(MathTask.Operation.class)), 5),
                "текстовые", new Quiz(new PoolTaskGenerator(
                        true,
                        new TextTask("У Вани было 10 шариков. 3 шарика улетели. Сколько шариков осталось у Вани?", "7"),
                        new TextTask("Катя собрала 8 цветов. 2 она подарила маме. Сколько цветов осталось у Кати?", "6"),
                        new TextTask("На столе лежало 6 пирожков. Петя съел 4 пирожка. Сколько пирожков осталось на столе?", "2"),
                        new TextTask("У Лизы было 12 конфет. 5 конфет она съела. Сколько конфет осталось у Лизы?", "7"),
                        new TextTask("Маша нарисовала 9 рисунков. Один рисунок она подарила подруге. Сколько рисунков осталось у Маши?", "8"),
                        new TextTask("У Коли было 15 карандашей. 7 карандашей он отдал брату. Сколько карандашей осталось у Коли?", "8"),
                        new TextTask("В коробке было 20 печенья. Миша съел 8 печенек. Сколько печенья осталось в коробке?", "12"),
                        new TextTask("Дима купил 7 книг. 2 книги он отдал сестре. Сколько книг осталось у Димы?", "5"),
                        new TextTask("На ёлке было 10 игрушек. 3 игрушки упали и разбились. Сколько игрушек осталось на ёлке?", "7")
                ), 10)
        );
    }

    public static void main(String[] args) throws QuizNotFinishedException {
        System.out.println("Введите название теста: ");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String name = scanner.nextLine().toLowerCase();
            if (!getQuizMap().containsKey(name)) {
                System.out.println("Неверное имя теста. Повторите попытку: ");
                continue;
            }
            Quiz quiz = getQuizMap().get(name);
            while (!quiz.isFinished()) {
                Task task = quiz.nextTask();
                System.out.println(task.getText());
                System.out.println("Ответ: ");
                String answer = scanner.nextLine();
                Result result = quiz.provideAnswer(answer);
                if (result == Result.INCORRECT_INPUT) {
                    System.out.println("Неверный ввод. Повторите попытку");
                } else if (result == Result.OK) {
                    System.out.println("Ответ верный");
                } else if (result == Result.WRONG) {
                    System.out.println("Ответ неверный");
                }
            }
            System.out.println("Твоя оценка: " + (quiz.getMark() * 10));
            break;
        }
    }

}
