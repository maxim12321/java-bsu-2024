package by.dilik14.quizer;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.EnumSet;

import by.dilik14.quizer.generators.DeadLineTaskGenerator;
import by.dilik14.quizer.generators.GroupTaskGenerator;
import by.dilik14.quizer.generators.PoolTaskGenerator;
import by.dilik14.quizer.generators.math.EquationTaskGenerator;
import by.dilik14.quizer.generators.math.ExpressionTaskGenerator;
import by.dilik14.quizer.tasks.TextTask;
import by.dilik14.quizer.tasks.math.MathTask.Operation;

public class Main {
    /**
     * @return тесты в {@link Map}, где
     *         ключ - название теста {@link String}
     *         значение - сам тест {@link Quiz}
     */
    static Map<String, Quiz> getQuizMap() {
        HashMap<String, Quiz> qmap = new HashMap<>();

        qmap.put("Pool with duplicates", new Quiz(
                new PoolTaskGenerator<>(
                        true,
                        new TextTask("Кто такой 🤓?", "Первая группа"),
                        new TextTask("Кто такой 🤡?", "Ты"),
                        new TextTask("Кто такой 😎?", "dilik14")),
                5));

        qmap.put("Pool without duplicates", new Quiz(
                new PoolTaskGenerator<>(
                        false,
                        new TextTask("Кто такой 🦆?", "Утка"),
                        new TextTask("Кто такой 🐄?", "Корова"),
                        new TextTask("Кто такой 🐵?", "Обезьяна"),
                        new TextTask("Кто такой 🐧?", "Пингвин")

                ), 3));

        qmap.put("Expression",
                new Quiz(new ExpressionTaskGenerator(-5, 5, EnumSet.of(Operation.ADDITION, Operation.DIVISION)), 3));

        qmap.put("Equation",
                new Quiz(new EquationTaskGenerator(-5, 5,
                        EnumSet.of(Operation.SUBTRACTION, Operation.MULTIPLICATION, Operation.DIVISION)), 3));

        qmap.put("Group", new Quiz(new GroupTaskGenerator<>(
                new PoolTaskGenerator<>(
                        true,
                        new TextTask("Кто такой 🤓?", "Первая группа"),
                        new TextTask("Кто такой 🤡?", "Ты"),
                        new TextTask("Кто такой 😎?", "dilik14")),
                new PoolTaskGenerator<>(
                        false,
                        new TextTask("Кто такой 🦆?", "Утка"),
                        new TextTask("Кто такой 🐄?", "Корова"),
                        new TextTask("Кто такой 🐵?", "Обезьяна"),
                        new TextTask("Кто такой 🐧?", "Пингвин")),
                new ExpressionTaskGenerator(-5, 5, EnumSet.of(Operation.ADDITION, Operation.DIVISION)),
                new EquationTaskGenerator(-5, 5,
                        EnumSet.of(Operation.SUBTRACTION, Operation.MULTIPLICATION, Operation.DIVISION))),
                4));

        qmap.put("Deadline", new Quiz(new DeadLineTaskGenerator(-5, 10), 5));

        return qmap;
    }

    public static void main(String[] args) {
        Map<String, Quiz> quizMap = getQuizMap();
        System.out.println("Доступные тесты😊:");
        quizMap.keySet().forEach(System.out::println);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите название теста😘:");
        String testName = scanner.nextLine();

        while (!quizMap.containsKey(testName)) {
            System.out.println("Такого теста не существует😔. Введите название теста:");
            testName = scanner.nextLine();
        }
        Quiz quiz = quizMap.get(testName);

        while (!quiz.isFinished()) {
            System.out.println(quiz.nextTask().getText());
            String answer = scanner.nextLine();
            Result result = quiz.provideAnswer(answer);

            System.out.println(switch (result) {
                case OK -> "OK 😴";
                case WRONG -> "WA 😈😈😈";
                case INCORRECT_INPUT -> "Ввод некорректный 😠😠😠";
            });
        }
        scanner.close();
        System.out.println("Ваша оценка😜: " + quiz.getMark());
    }
}
