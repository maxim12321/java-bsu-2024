import core.Pair;
import core.Result;
import core.Task;
import generators.EquationTaskGenerator;
import generators.ExpressionTaskGenerator;
import generators.GroupTaskGenerator;
import generators.PoolTaskGenerator;
import tasks.TextTask;
import tasks.math.MathTask;

import java.util.*;

public class Main {
    /**
     * @return тесты в {@link Map}, где
     * ключ - название теста {@link String}, значение - сам тест {@link Quiz}
     */
    static Map<String, Quiz> getQuizMap() {
        HashMap<String, Quiz> map = new HashMap<>();
        map.put("Basic Addition", new Quiz(
                new ExpressionTaskGenerator(
                        0,
                        10,
                        EnumSet.of(MathTask.Operation.ADDITION)
                ), 5)
        );

        // Тест для вычитания
        map.put("Basic Subtraction", new Quiz(
                new ExpressionTaskGenerator(
                        0,
                        20,
                        EnumSet.of(MathTask.Operation.SUBTRACTION)
                ), 5)
        );

        // Тест для умножения
        map.put("Basic Multiplication", new Quiz(
                new ExpressionTaskGenerator(
                        1,
                        10,
                        EnumSet.of(MathTask.Operation.MULTIPLICATION)
                ), 5)
        );

        // Тест для деления
        map.put("Basic Division", new Quiz(
                new ExpressionTaskGenerator(
                        1,
                        10,
                        EnumSet.of(MathTask.Operation.DIVISION)
                ), 5)
        );

        // Тест для смешанных операций
        map.put("Mixed Operations", new Quiz(
                new ExpressionTaskGenerator(
                        0,
                        10,
                        EnumSet.of(MathTask.Operation.ADDITION, MathTask.Operation.SUBTRACTION, MathTask.Operation.MULTIPLICATION, MathTask.Operation.DIVISION)
                ), 10)
        );

        // Тест для деления на ноль
        map.put("Division by Zero", new Quiz(
                new ExpressionTaskGenerator(
                        0,
                        10,
                        EnumSet.of(MathTask.Operation.DIVISION)
                ), 5)
        );


        // Тесты для EquationTaskGenerator
        map.put("Equation Addition", new Quiz(
                new EquationTaskGenerator(
                        0,
                        10,
                        EnumSet.of(MathTask.Operation.ADDITION)
                ), 5)
        );

        map.put("Equation Subtraction", new Quiz(
                new EquationTaskGenerator(
                        0,
                        20,
                        EnumSet.of(MathTask.Operation.SUBTRACTION)
                ), 5)
        );

        map.put("Equation Multiplication", new Quiz(
                new EquationTaskGenerator(
                        1,
                        10,
                        EnumSet.of(MathTask.Operation.MULTIPLICATION)
                ), 5)
        );

        map.put("Equation Division", new Quiz(
                new EquationTaskGenerator(
                        1,
                        10,
                        EnumSet.of(MathTask.Operation.DIVISION)
                ), 5)
        );

        map.put("Equation Mixed Operations", new Quiz(
                new EquationTaskGenerator(
                        0,
                        10,
                        EnumSet.of(MathTask.Operation.ADDITION, MathTask.Operation.SUBTRACTION, MathTask.Operation.MULTIPLICATION, MathTask.Operation.DIVISION)
                ), 10)
        );

        map.put("Min Greater Than Max, fail", new Quiz(
                new EquationTaskGenerator(
                        10,
                        5,
                        EnumSet.of(MathTask.Operation.ADDITION)
                ), 5)
        );

        map.put("Invalid Operation, fail", new Quiz(
                new EquationTaskGenerator(
                        0,
                        10,
                        EnumSet.noneOf(MathTask.Operation.class)
                ), 5)
        );

        map.put("Always Zero, fail", new Quiz(
                new ExpressionTaskGenerator(
                        0,
                        0,
                        EnumSet.of(MathTask.Operation.DIVISION)
                ), 5)
        );

        // Тест с PoolTaskGenerator
        Collection<Task> tasks = List.of(
                new TextTask("What is the capital of France?", "Paris"),
                new TextTask("What is the largest planet in our solar system?", "Jupiter")
        );
        map.put("Pool with Duplicates", new Quiz(
                new PoolTaskGenerator(
                        true,
                        new TextTask("What is the capital of Germany?", "Berlin"),
                        new TextTask("What color is the sky on a clear day?", "Blue"),
                        new TextTask("What is the largest planet in our solar system?", "Jupiter")
                ), 5)
        );

        map.put("Pool without Duplicates", new Quiz(
                new PoolTaskGenerator(
                        false,
                        tasks
                ), 3)
        );

        map.put("Empty Pool, fail", new Quiz(
                new PoolTaskGenerator(
                        false
                ), 1)
        );

        // Тест с GroupTaskGenerator
        map.put("Group", new Quiz(
                new GroupTaskGenerator(
                        List.of(
                                new ExpressionTaskGenerator(0, 10, EnumSet.of(MathTask.Operation.ADDITION)),
                                new EquationTaskGenerator(1, 10, EnumSet.of(MathTask.Operation.MULTIPLICATION))
                        )
                ), 5)
        );

        // Тесты для GroupTaskGenerator
        map.put("Group Basic Operations", new Quiz(
                new GroupTaskGenerator(
                        new ExpressionTaskGenerator(0, 10, EnumSet.of(MathTask.Operation.ADDITION)),
                        new ExpressionTaskGenerator(0, 10, EnumSet.of(MathTask.Operation.SUBTRACTION))
                ), 5)
        );

        map.put("Group Mixed Operations", new Quiz(
                new GroupTaskGenerator(
                        new ExpressionTaskGenerator(0, 10, EnumSet.of(MathTask.Operation.ADDITION, MathTask.Operation.SUBTRACTION)),
                        new EquationTaskGenerator(1, 10, EnumSet.of(MathTask.Operation.MULTIPLICATION, MathTask.Operation.DIVISION))
                ), 10)
        );

        map.put("Group with Invalid Generator, fail", new Quiz(
                new GroupTaskGenerator(
                        new ExpressionTaskGenerator(0, 10, EnumSet.of(MathTask.Operation.ADDITION)),
                        new EquationTaskGenerator(10, 5, EnumSet.of(MathTask.Operation.ADDITION))
                ), 5)
        );
        
        return map;
    }

    static Map<String, Quiz> getQuizMap2() {
        HashMap<String, Quiz> map = new HashMap<>();
        map.put("Basic Addition", new Quiz(
                        ExpressionTaskGenerator.class, 5, Map.of(
                        "minNumber", 0,
                        "maxNumber", 10,
                        "operations", EnumSet.of(MathTask.Operation.ADDITION)
                ))
        );

        // Тест для вычитания
        map.put("Basic Subtraction", new Quiz(
                        ExpressionTaskGenerator.class, 5, Map.of(
                        "minNumber", 0,
                        "maxNumber", 20,
                        "operations", EnumSet.of(MathTask.Operation.SUBTRACTION)
                ))
        );

        // Тест для умножения
        map.put("Basic Multiplication", new Quiz(
                        ExpressionTaskGenerator.class, 5, Map.of(
                        "minNumber", 1,
                        "maxNumber", 10,
                        "operations", EnumSet.of(MathTask.Operation.MULTIPLICATION)
                ))
        );

        // Тест для деления
        map.put("Basic Division", new Quiz(
                        ExpressionTaskGenerator.class, 5, Map.of(
                        "minNumber", 1,
                        "maxNumber", 10,
                        "operations", EnumSet.of(MathTask.Operation.DIVISION)
                ))
        );

        // Тест для смешанных операций
        map.put("Mixed Operations", new Quiz(
                        ExpressionTaskGenerator.class, 10, Map.of(
                        "minNumber", 0,
                        "maxNumber", 10,
                        "operations", EnumSet.of(MathTask.Operation.ADDITION, MathTask.Operation.SUBTRACTION, MathTask.Operation.MULTIPLICATION, MathTask.Operation.DIVISION)
                ))
        );

        // Тест для деления на ноль
        map.put("Division by Zero", new Quiz(
                        ExpressionTaskGenerator.class, 5, Map.of(
                        "minNumber", 0,
                        "maxNumber", 10,
                        "operations", EnumSet.of(MathTask.Operation.DIVISION)
                ))
        );

        // Тесты для EquationTaskGenerator
        map.put("Equation Addition", new Quiz(
                        EquationTaskGenerator.class, 5, Map.of(
                        "minNumber", 0,
                        "maxNumber", 10,
                        "operations", EnumSet.of(MathTask.Operation.ADDITION)
                ))
        );

        map.put("Equation Subtraction", new Quiz(
                        EquationTaskGenerator.class, 5, Map.of(
                        "minNumber", 0,
                        "maxNumber", 20,
                        "operations", EnumSet.of(MathTask.Operation.SUBTRACTION)
                ))
        );

        map.put("Equation Multiplication", new Quiz(
                        EquationTaskGenerator.class, 5, Map.of(
                        "minNumber", 1,
                        "maxNumber", 10,
                        "operations", EnumSet.of(MathTask.Operation.MULTIPLICATION)
                ))
        );

        map.put("Equation Division", new Quiz(
                        EquationTaskGenerator.class, 5, Map.of(
                        "minNumber", 1,
                        "maxNumber", 10,
                        "operations", EnumSet.of(MathTask.Operation.DIVISION)
                ))
        );

        map.put("Equation Mixed Operations", new Quiz(
                        EquationTaskGenerator.class, 10, Map.of(
                        "minNumber", 0,
                        "maxNumber", 10,
                        "operations", EnumSet.of(MathTask.Operation.ADDITION, MathTask.Operation.SUBTRACTION, MathTask.Operation.MULTIPLICATION, MathTask.Operation.DIVISION)
                ))
        );

        map.put("Min Greater Than Max, fail", new Quiz(
                        EquationTaskGenerator.class, 5, Map.of(
                        "minNumber", 10,
                        "maxNumber", 5,
                        "operations", EnumSet.of(MathTask.Operation.ADDITION)
                ))
        );

        map.put("Invalid Operation, fail", new Quiz(
                        EquationTaskGenerator.class, 5, Map.of(
                        "minNumber", 0,
                        "maxNumber", 10,
                        "operations", EnumSet.noneOf(MathTask.Operation.class)
                ))
        );

        map.put("Always Zero, fail", new Quiz(
                        ExpressionTaskGenerator.class, 5, Map.of(
                        "minNumber", 0,
                        "maxNumber", 0,
                        "operations", EnumSet.of(MathTask.Operation.DIVISION)
                ))
        );

        // Тесты для PoolTaskGenerator
        Collection<Task> tasks = List.of(
                new TextTask("What is the capital of France?", "Paris"),
                new TextTask("What is the largest planet in our solar system?", "Jupiter")
        );
        map.put("Pool with Duplicates", new Quiz(
                        PoolTaskGenerator.class, 5, Map.of(
                        "allowDuplicates", true,
                        "tasks", List.of(
                                new TextTask("What is the capital of Germany?", "Berlin"),
                                new TextTask("What color is the sky on a clear day?", "Blue"),
                                new TextTask("What is the largest planet in our solar system?", "Jupiter")
                        )
                ))
        );

        map.put("Pool without Duplicates", new Quiz(
                        PoolTaskGenerator.class, 3, Map.of(
                        "allowDuplicates", false,
                        "tasks", tasks
                ))
        );

        map.put("Empty Pool, fail", new Quiz(
                        PoolTaskGenerator.class, 1, Map.of(
                        "allowDuplicates", false
                ))
        );

        // Тесты для GroupTaskGenerator
        map.put("Group Basic Operations", new Quiz(
                GroupTaskGenerator.class, 5, Map.of(
                "generatorArgsList", List.of(
                        new Pair<>(ExpressionTaskGenerator.class, Map.of(
                                "minNumber", 0,
                                "maxNumber", 10,
                                "operations", EnumSet.of(MathTask.Operation.ADDITION)
                        )),
                        new Pair<>(ExpressionTaskGenerator.class, Map.of(
                                "minNumber", 0,
                                "maxNumber", 10,
                                "operations", EnumSet.of(MathTask.Operation.SUBTRACTION)
                        ))
                )
        )));

        map.put("Group Mixed Operations", new Quiz(
                GroupTaskGenerator.class, 10, Map.of(
                "generatorArgsList", List.of(
                        new Pair<>(ExpressionTaskGenerator.class, Map.of(
                                "minNumber", 0,
                                "maxNumber", 10,
                                "operations", EnumSet.of(MathTask.Operation.ADDITION, MathTask.Operation.SUBTRACTION)
                        )),
                        new Pair<>(EquationTaskGenerator.class, Map.of(
                                "minNumber", 1,
                                "maxNumber", 10,
                                "operations", EnumSet.of(MathTask.Operation.MULTIPLICATION, MathTask.Operation.DIVISION)
                        ))
                )
        )));

        map.put("Group with Invalid Generator, fail", new Quiz(
                GroupTaskGenerator.class, 5, Map.of(
                "generatorArgsList", List.of(
                        new Pair<>(ExpressionTaskGenerator.class, Map.of(
                                "minNumber", 0,
                                "maxNumber", 10,
                                "operations", EnumSet.of(MathTask.Operation.ADDITION)
                        )),
                        new Pair<>(EquationTaskGenerator.class, Map.of(
                                "minNumber", 10,
                                "maxNumber", 5,
                                "operations", EnumSet.of(MathTask.Operation.ADDITION)
                        ))
                )
        )));

        return map;
    }

    private static void runQuiz(Quiz quiz, Scanner scanner) {
        System.out.println("Начинаем тест. Отвечайте на вопросы, пожалуйста.");

        while (!quiz.isFinished()) {
            System.out.println(quiz.nextTask().getText());
            String answer = scanner.nextLine();
            Result result = quiz.provideAnswer(answer);
            System.out.println(switch (result) {
                case OK -> "Ответ верный.";
                case WRONG -> "Ответ неверный.";
                case INCORRECT_INPUT -> "Ввод некорректный. Перечитайте задание внимательно и введите ответ заново.";
            });
        }

        System.out.println("Тест пройден. Ваша оценка: " + quiz.getMark() + " из 1");
    }

    public static void main(String[] args) {
        Map<String, Quiz> quizMap = getQuizMap2();
        System.out.println("Список тестов:");
        quizMap.keySet().forEach(System.out::println);

        try (Scanner scanner = new Scanner(System.in)) {
            String testName;
            do {
                System.out.println("Введите название теста.");
                testName = scanner.nextLine();
                if (!quizMap.containsKey(testName)) {
                    System.out.println("Такого теста не существует.");
                } else if (testName.endsWith("fail")) {
                    System.out.println("Тест вызовет ошибку и не предназначен для прохождения учеником. Вы уверены, что хотите его запустить? (y/n)");
                    if (!scanner.nextLine().equals("y")) {
                        runQuiz(quizMap.get(testName), scanner);
                        return;
                    }
                }
            } while (!quizMap.containsKey(testName));

            runQuiz(quizMap.get(testName), scanner);
        }
    }
}