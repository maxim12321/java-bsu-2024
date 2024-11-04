package by.KirillBukato.quizer;

import by.KirillBukato.quizer.generators.GroupTaskGenerator;
import by.KirillBukato.quizer.generators.PoolTaskGenerator;
import by.KirillBukato.quizer.generators.TextVariantTaskGenerator;
import by.KirillBukato.quizer.generators.math.EquationTaskGenerator;
import by.KirillBukato.quizer.generators.math.SimpleExpressionTaskGenerator;
import by.KirillBukato.quizer.generators.math.StoryExpressionTaskGenerator;
import by.KirillBukato.quizer.generators.math.VariantExpressionTaskGenerator;
import by.KirillBukato.quizer.tasks.TextTask;
import by.KirillBukato.quizer.tasks.math.MathOperation;
import by.KirillBukato.quizer.tasks.math.MathStory;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    /**
     * @return тесты в {@link Map}, где
     * ключ - название теста {@link String}, значение - сам тест {@link Quiz}
     */
    static Map<String, Quiz> getQuizMap() {
        HashMap<String, Quiz> map = new HashMap<>();
        map.put("Basic", new Quiz(
                new SimpleExpressionTaskGenerator(
                        0,
                        10,
                        EnumSet.of(MathOperation.ADD)
                ), 5)
        );
        map.put("Basic Expressions", new Quiz(
                new SimpleExpressionTaskGenerator(
                        0,
                        20,
                        EnumSet.of(MathOperation.ADD,
                                MathOperation.SUBTRACT,
                                MathOperation.MULTIPLY)
                ), 10
        ));
        map.put("Basic Equations", new Quiz(
                new EquationTaskGenerator(
                        0,
                        10,
                        EnumSet.of(MathOperation.ADD,
                                MathOperation.SUBTRACT)
                ), 5
        ));
        map.put("Pool with duplicates", new Quiz(
                new PoolTaskGenerator<>(
                        true,
                        new TextTask("Как зовут Олега?", "Олег"),
                        new TextTask("Как зовут Виктора?", "Виктор")
                ), 3
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
        map.put("Pool without duplicates, no fail", new Quiz(
                new PoolTaskGenerator<>(
                        false,
                        new TextTask("Как зовут Олега?", "Олег"),
                        new TextTask("Как зовут Виктора?", "Виктор")
                ), 2
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
        map.put("Group, no fail", new Quiz(
                new GroupTaskGenerator<>(
                        new PoolTaskGenerator<>(
                                false,
                                new TextTask("Как зовут Олега?", "Олег"),
                                new TextTask("Как зовут Виктора?", "Виктор")),
                        new SimpleExpressionTaskGenerator(
                                0,
                                10,
                                EnumSet.of(MathOperation.ADD)
                        )
                ), 10
        ));
        map.put("Zero division avoided, expressions", new Quiz(
                new SimpleExpressionTaskGenerator(
                        0,
                        1,
                        EnumSet.of(MathOperation.DIVIDE)
                ), 10
        ));
        map.put("Zero division avoided, equations", new Quiz(
                new EquationTaskGenerator(
                        0,
                        1,
                        EnumSet.of(MathOperation.MULTIPLY,
                                MathOperation.DIVIDE)
                ), 10
        ));
        map.put("TaskVariant", new Quiz(
                new TextVariantTaskGenerator(
                        "Как зовут Олега?",
                        "Олег",
                        "Виктор",
                        "Родион"
                ), 5
        ));
        map.put("Expression TaskVariant", new Quiz(
                new VariantExpressionTaskGenerator(0, 10,
                        EnumSet.of(
                                MathOperation.ADD,
                                MathOperation.SUBTRACT,
                                MathOperation.MULTIPLY)
                ), 5
        ));
        map.put("Stories", new Quiz(
                new StoryExpressionTaskGenerator(
                        0,
                        20,
                        EnumSet.of(MathOperation.ADD,
                                MathOperation.SUBTRACT,
                                MathOperation.MULTIPLY)
                ), 10
        ));
        map.put("Stories about apples", new Quiz(
                new StoryExpressionTaskGenerator(
                        0,
                        20,
                        EnumSet.of(MathOperation.ADD,
                                MathOperation.SUBTRACT,
                                MathOperation.MULTIPLY,
                                MathOperation.DIVIDE),
                        EnumSet.of(MathStory.APPLES)
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
