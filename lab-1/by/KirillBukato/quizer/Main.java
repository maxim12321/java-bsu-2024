package by.KirillBukato.quizer;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import by.KirillBukato.quizer.generators.GroupTaskGenerator;
import by.KirillBukato.quizer.generators.PoolTaskGenerator;
import by.KirillBukato.quizer.generators.TextVariantTaskGenerator;
import by.KirillBukato.quizer.generators.math.ExpressionTaskGenerator;
import by.KirillBukato.quizer.generators.math.EquationTaskGenerator;
import by.KirillBukato.quizer.generators.math.ExpressionVariantTaskGenerator;
import by.KirillBukato.quizer.tasks.TextTask;
import by.KirillBukato.quizer.tasks.math.MathTask;

public class Main {
    /**
     * @return тесты в {@link Map}, где
     * ключ     - название теста {@link String}
     * значение - сам тест       {@link Quiz}
     */
    static Map<String, Quiz> getQuizMap() {
        HashMap<String, Quiz> map = new HashMap<>();
        map.put("Basic", new Quiz(
                new ExpressionTaskGenerator(
                        0,
                        10,
                        EnumSet.of(MathTask.Operation.ADD)
                ), 5)
        );
        map.put("Basic Expressions", new Quiz(
                new ExpressionTaskGenerator(
                        0,
                        20,
                        EnumSet.of(MathTask.Operation.ADD,
                                MathTask.Operation.SUBTRACT,
                                MathTask.Operation.MULTIPLY)
                ), 10
        ));
        map.put("Basic Equations", new Quiz(
                new EquationTaskGenerator(
                        0,
                        10,
                        EnumSet.of(MathTask.Operation.ADD,
                                MathTask.Operation.SUBTRACT)
                ), 5
        ));
        map.put("Pool with duplicates", new Quiz(
                new PoolTaskGenerator(
                        true,
                        new TextTask("Как зовут Олега?", "Олег"),
                        new TextTask("Как зовут Виктора?", "Виктор")
                ), 3
        ));
        map.put("Pool without duplicates, fail", new Quiz(
                new PoolTaskGenerator(
                        false,
                        new TextTask("Как зовут Олега?", "Олег"),
                        new TextTask("Как зовут Родиона?", "Родион"),
                        new TextTask("Как зовут Никиту?", "Никита"),
                        new TextTask("Как зовут Виктора?", "Виктор")

                ), 5
        ));
        map.put("Pool without duplicates, no fail", new Quiz(
                new PoolTaskGenerator(
                        false,
                        new TextTask("Как зовут Олега?", "Олег"),
                        new TextTask("Как зовут Виктора?", "Виктор")
                ), 2
        ));
        map.put("Group, fail", new Quiz(
                new GroupTaskGenerator(
                        new PoolTaskGenerator(
                                false,
                                new TextTask("Как зовут Олега?", "Олег"),
                                new TextTask("Как зовут Виктора?", "Виктор")),
                        new PoolTaskGenerator(
                                false,
                                new TextTask("Как зовут Диму?", "Дима"),
                                new TextTask("Как зовут Вадима?", "Вадим"))
                        ), 5
        ));
        map.put("Group, no fail", new Quiz(
                new GroupTaskGenerator(
                        new PoolTaskGenerator(
                                false,
                                new TextTask("Как зовут Олега?", "Олег"),
                                new TextTask("Как зовут Виктора?", "Виктор")),
                        new ExpressionTaskGenerator(
                                0,
                                10,
                                EnumSet.of(MathTask.Operation.ADD)
                        )
                ), 10
        ));
        map.put("Zero division avoided, expressions", new Quiz(
                new ExpressionTaskGenerator(
                        0,
                        1,
                        EnumSet.of(MathTask.Operation.DIVIDE)
                ), 10
        ));
        map.put("Zero division avoided, equations", new Quiz(
                new EquationTaskGenerator(
                        0,
                        1,
                        EnumSet.of(MathTask.Operation.MULTIPLY,
                                MathTask.Operation.DIVIDE)
                ), 10
        ));
        map.put("Variants", new Quiz(
                new TextVariantTaskGenerator(
                        "Как зовут Олега?",
                        "Олег",
                        "Виктор",
                        "Родион"
                ), 5
        ));

        map.put("Expression Variants", new Quiz(
                new ExpressionVariantTaskGenerator(0, 10,
                        EnumSet.of(
                                MathTask.Operation.ADD,
                                MathTask.Operation.SUBTRACT,
                                MathTask.Operation.MULTIPLY)
                ), 5
        ));

        //Падает на созданных исключениях, если раскомментировать.
        //Причина: нельзя создавать генераторы, которые создают только неправильные задания

//        map.put("Zero division exception, expressions", new Quiz(
//                new ExpressionTaskGenerator(
//                        0,
//                        0,
//                        EnumSet.of(MathTask.Operation.DIVIDE)
//                ), 10
//        ));
//        map.put("Zero division exception, equations", new Quiz(
//                new EquationTaskGenerator(
//                        0,
//                        0,
//                        EnumSet.of(MathTask.Operation.MULTIPLY,
//                                MathTask.Operation.DIVIDE)
//                ), 10
//        ));
//        map.put("Bounds exception, expressions", new Quiz(
//                new ExpressionTaskGenerator(
//                        0,
//                        -4,
//                        EnumSet.of(MathTask.Operation.ADD)
//                ), 10
//        ));

        return map;
    }

    public static void main(String[] args) {
        Map<String, Quiz> quizMap = getQuizMap();

        System.out.println("Список тестов:");
        for(var quiz : quizMap.entrySet()) {
            System.out.println(quiz.getKey());
        }
        String testName;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("Введите название теста...");
            testName = scanner.nextLine();
        } while (!quizMap.containsKey(testName));
        Quiz quiz = quizMap.get(testName);

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

        System.out.println("Поздравляем с успешным прохождением теста! Ваша оценка: " + quiz.getMark());
    }
}
