package by.pkasila.quizer;

import by.pkasila.quizer.common.MathOperation;
import by.pkasila.quizer.common.Quiz;
import by.pkasila.quizer.common.Result;
import by.pkasila.quizer.generators.GroupTaskGenerator;
import by.pkasila.quizer.generators.PoolTaskGenerator;
import by.pkasila.quizer.generators.TextVariantTaskGenerator;
import by.pkasila.quizer.generators.math.EquationTaskGenerator;
import by.pkasila.quizer.generators.math.ExpressionTaskGenerator;
import by.pkasila.quizer.generators.math.VariantExpressionTaskGenerator;
import by.pkasila.quizer.tasks.TextTask;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    /**
     * @return тесты в {@link Map}, где
     * ключ     - название теста {@link String}
     * значение - сам тест       {@link Quiz}
     */
    static Map<String, Quiz> getQuizMap() {
        HashMap<String, Quiz> map = new HashMap<>();
        map.put("BasicSum", new Quiz(
                new ExpressionTaskGenerator(
                        0,
                        100,
                        EnumSet.of(MathOperation.SUM)
                ), 10)
        );
        map.put("BasicExpressions", new Quiz(
                new ExpressionTaskGenerator(
                        0,
                        100,
                        EnumSet.of(MathOperation.SUM,
                                MathOperation.DIFFERENCE,
                                MathOperation.MULTIPLICATION)
                ), 10
        ));
        map.put("BasicEquations", new Quiz(
                new EquationTaskGenerator(
                        0,
                        100,
                        EnumSet.of(MathOperation.SUM,
                                MathOperation.DIFFERENCE)
                ), 10
        ));
        map.put("PoolDups", new Quiz(
                new PoolTaskGenerator<>(
                        true,
                        new TextTask("Когда Капустник? (дд.мм.гггг)", "06.11.2024"),
                        new TextTask("Кто такой Андрей?", "Бог")
                ), 3
        ));
        map.put("PoolFailedDups", new Quiz(
                new PoolTaskGenerator<>(
                        false,
                        new TextTask("Когда Капустник? (дд.мм.гггг)", "06.11.2024"),
                        new TextTask("Кто такой Андрей?", "Бог"),
                        new TextTask("Где работает Андрей?", "Google"),
                        new TextTask("Где работал Андрей раньше?", "Лицей")

                ), 5
        ));
        map.put("PoolDupEqual", new Quiz(
                new PoolTaskGenerator<>(
                        false,
                        new TextTask("Когда Капустник? (дд.мм.гггг)", "06.11.2024"),
                        new TextTask("Кто такой Андрей?", "Бог")
                ), 2
        ));
        map.put("GroupFail", new Quiz(
                new GroupTaskGenerator<>(
                        new PoolTaskGenerator<>(
                                false,
                                new TextTask("Когда Капустник? (дд.мм.гггг)", "06.11.2024"),
                                new TextTask("Кто такой Андрей?", "Бог")),
                        new PoolTaskGenerator<>(
                                false,
                                new TextTask("Где работает Андрей?", "Google"),
                                new TextTask("Где работал Андрей раньше?", "Лицей"))
                ), 5
        ));
        map.put("GroupNoFail", new Quiz(
                new GroupTaskGenerator<>(
                        new PoolTaskGenerator<>(
                                false,
                                new TextTask("Где работает Андрей?", "Google"),
                                new TextTask("Где работал Андрей раньше?", "Лицей")),
                        new ExpressionTaskGenerator(
                                0,
                                10,
                                EnumSet.of(MathOperation.SUM)
                        )
                ), 10
        ));
        map.put("ExpressionsAvoidZeroDiv", new Quiz(
                new ExpressionTaskGenerator(
                        0,
                        1,
                        EnumSet.of(MathOperation.DIVISION)
                ), 10
        ));
        map.put("EquationsAvoidZeroDiv", new Quiz(
                new EquationTaskGenerator(
                        0,
                        1,
                        EnumSet.of(MathOperation.MULTIPLICATION,
                                MathOperation.DIVISION)
                ), 10
        ));
        map.put("TaskVariant", new Quiz(
                new TextVariantTaskGenerator(
                        "Как зовут Ильина?",
                        "Андрей Викторович",
                        "Виктор Павлович",
                        "Павел Андреевич"
                ), 10
        ));
        map.put("ExpressionTaskVariant", new Quiz(
                new VariantExpressionTaskGenerator(0, 10,
                        EnumSet.of(
                                MathOperation.SUM,
                                MathOperation.DIFFERENCE,
                                MathOperation.MULTIPLICATION)
                ), 10
        ));
        return map;
    }

    public static void main(String[] args) {
        Map<String, Quiz> quizMap = getQuizMap();
        System.out.println("List of all available tests:");
        quizMap.keySet().forEach(System.out::println);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the test name:");
        String testName = scanner.nextLine();
        while (!quizMap.containsKey(testName)) {
            System.out.println("This test does not exists! Please enter the correct key.");
            testName = scanner.nextLine();
        }
        Quiz quiz = quizMap.get(testName);

        System.out.println("Let's get started! Answer the questions:");
        while (!quiz.isFinished()) {
            System.out.println(quiz.nextTask().getText());
            String answer = scanner.nextLine();
            Result result = quiz.provideAnswer(answer);
            System.out.println(switch (result) {
                case OK -> "The answer is correct.";
                case WRONG -> "The answer is incorrect.";
                case INCORRECT_INPUT -> "The input is invalid. Try entering again.";
            });
        }
        scanner.close();
        System.out.println("You completed the test! Your mark: " + quiz.getMark());
    }
}