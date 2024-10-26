package by.MikhailNaumovich.quizer;

import by.MikhailNaumovich.quizer.generators.PoolTaskGenerator;
import by.MikhailNaumovich.quizer.generators.GroupTaskGenerator;
import by.MikhailNaumovich.quizer.generators.math.EquationTaskGenerator;
import by.MikhailNaumovich.quizer.generators.math.ExpressionTaskGenerator;
//import by.MikhailNaumovich.quizer.tasks.TextTask;

import java.util.EnumSet;

import by.MikhailNaumovich.quizer.tasks.TextTask;
import by.MikhailNaumovich.quizer.tasks.math.MathEnum;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Main {
    public static Map<String, Quiz> getQuizMap() {
        HashMap<String, Quiz> map = new HashMap<>();
        map.put("Basic Equations", new Quiz(
                new EquationTaskGenerator(
                        10,
                        10,
                        EnumSet.of(MathEnum.ADD,
                                MathEnum.SUBTRACT)
                ), 5
        ));

        map.put("Basic Expressions", new Quiz(
                new ExpressionTaskGenerator(
                        0,
                        20,
                        EnumSet.of(MathEnum.ADD,
                                MathEnum.SUBTRACT,
                                MathEnum.MULTIPLY)
                ), 5
        ));

        map.put("Pool with duplicates", new Quiz(
                new PoolTaskGenerator<>(
                        true,
                        new TextTask("Как зовут Олега?", "Олег"),
                        new TextTask("Как зовут Виктора?", "Виктор")
                ), 6
        ));

        map.put("Pool without duplicates, fail", new Quiz(
                new PoolTaskGenerator<>(
                        false,
                        new TextTask("Как зовут Олега?", "Олег"),
                        new TextTask("Как зовут Родиона?", "Родион"),
                        new TextTask("Как зовут Никиту?", "Никита"),
                        new TextTask("Как зовут Виктора?", "Виктор")

                ), 5
        ));

        map.put("Group, fail", new Quiz(
                new GroupTaskGenerator<>(
                        new PoolTaskGenerator<>(
                                false,
                                new TextTask("Как зовут Олега?", "Олег"),
                                new TextTask("Как зовут Виктора?", "Виктор")),
                        new PoolTaskGenerator<>(
                                false,
                                new TextTask("Как зовут Диму?", "Дима"),
                                new TextTask("Как зовут Вадима?", "Вадим"))
                ), 5
        ));

        map.put("Zero division avoided, expressions", new Quiz(
                new ExpressionTaskGenerator(
                        0,
                        1,
                        EnumSet.of(MathEnum.DIVIDE)
                ), 10
        ));
        map.put("Zero division avoided, equations", new Quiz(
                new EquationTaskGenerator(
                        0,
                        1,
                        EnumSet.of(MathEnum.MULTIPLY,
                                MathEnum.DIVIDE)
                ), 10
        ));

        map.put("Division", new Quiz(
                new ExpressionTaskGenerator(
                        6,
                        7,
                        EnumSet.of(MathEnum.DIVIDE)
                ), 10
        ));

        
        return map;

    }
    public static void main(String[] args) {
        Map<String, Quiz> quizMap = getQuizMap();
        System.out.println("Список тестов:");
        quizMap.keySet().forEach(System.out::println);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите название теста...");
        String testName = scanner.nextLine();
        while (!quizMap.containsKey(testName)) {
            System.out.println("Такого теста не существует. Введите название теста...");
            testName = scanner.nextLine();
        }
        Quiz quiz = quizMap.get(testName);

        System.out.println("Начинаем тест. Отвечайте на вопросы, пожалуйста.");
        while (!quiz.isFinished()) {
            System.out.println(quiz.nextTask().getText());
            String answer = scanner.nextLine();
            Result result = quiz.provideAnswer(answer);
            System.out.println(switch (result) {
                case OK -> "Ответ верный!";
                case WRONG -> "Ответ неверный!";
                case INCORRECT_INPUT -> "Ввод некорректный. Перечитайте задание внимательно и попробуйте ещё раз!";
            });
        }
        scanner.close();
        System.out.println("Поздравляем с успешным прохождением теста! Ваша оценка: " + quiz.getMark());
    }
}
