package by.mmaxemm.quizer;
import java.util.*;

import by.mmaxemm.quizer.exceptions.*;
import by.mmaxemm.quizer.generators.*;
import by.mmaxemm.quizer.generators.math.*;
import by.mmaxemm.quizer.tasks.*;
import by.mmaxemm.quizer.tasks.math.*;

public class Main {
    public static void main(String[] args) {
        Map<String, Quiz> quizMap = getQuizMap();

        boolean wantsToContinue = true;
        Scanner scanner = new Scanner(System.in);

        while(wantsToContinue) {
            System.out.println("Список тестов:");
            quizMap.keySet().forEach(System.out::println);
            System.out.println("Введите название теста:");
            String testName = scanner.nextLine();
            while (!quizMap.containsKey(testName)) {
                if(testName.isBlank()) {
                    testName = scanner.nextLine();
                    continue;
                }
                System.out.println("Такого теста не существует. Введите название теста:");
                testName = scanner.nextLine();
            }
            Quiz quiz = quizMap.get(testName);

            System.out.println("Начинаем тест. Отвечайте на вопросы, пожалуйста.");
            while (!quiz.isFinished()) {
                System.out.println(quiz.nextTask().getText());
                String answer = scanner.nextLine();
                while (answer.isBlank()) {
                    answer = scanner.nextLine();
                }
                Result result = quiz.provideAnswer(answer);
                System.out.println(switch (result) {
                    case OK -> "Ответ верный!";
                    case WRONG -> "Ответ неверный!";
                    case INCORRECT_INPUT -> "Ввод некорректный. Перечитайте задание внимательно и попробуйте ещё раз!";
                });
            }
            try {
                System.out.println("Поздравляем с успешным прохождением теста! Ваша оценка: " + quiz.getMark());
            } catch (QuizNotFinishedException e) {
                System.out.println(e.getMessage());
            }
            System.out.println("Хотите пройти другой тест? (да/нет)");
            String usersWish = scanner.nextLine();
            while (!usersWish.equalsIgnoreCase("да") && !usersWish.equalsIgnoreCase("нет")) {
                if(usersWish.isBlank()) {
                    usersWish = scanner.nextLine();
                    continue;
                }
                System.out.println("Введите 'да' или 'нет'");
                usersWish = scanner.nextLine();
            }
            if(usersWish.equalsIgnoreCase("нет")) {
                wantsToContinue = false;
            }
        }
        scanner.close();
    }

    /**
     * @return тесты в {@link Map}, где
     * ключ     - название теста {@link String}
     * значение - сам тест       {@link Quiz}
     */
    static Map<String, Quiz> getQuizMap() {
        HashMap<String, Quiz> testMap = new HashMap<>();
        testMap.put("Pool of tasks, duplicates allowed", new Quiz(
                new PoolTaskGenerator(true,
                        new TextTask("Как дела?", "норм"),
                        new TextTask(
        "Рисуют на стене футбольные ворота, а на полу — мяч. Говорят забить гол. Что будешь делать?",
                                "попрошу дать пас"),
                        new EquationTask(1, 10, MathTask.Operation.ADDITION),
                        new ExpressionTask(2, 20, MathTask.Operation.SUBSTRACTION)
                ), 5)
        );

        Collection<Task> tasks = new LinkedList<>();
        tasks.add(new TextTask("Сколько всего цифр?", "10"));
        tasks.add(new TextTask(
        "Тебе дают в руки веник и говорят: «Сыграй на гитаре что-нибудь». Что будешь делать?",
                "попрошу настроить"));
        testMap.put("Pool of tasks, no duplicates", new Quiz(
                new PoolTaskGenerator(false, tasks), 3));

        testMap.put("Expressions, no doubles", new Quiz(
                new ExpressionTaskGenerator(0,
                        42,
                        EnumSet.of(MathTask.Operation.ADDITION,
                                MathTask.Operation.SUBSTRACTION,
                                MathTask.Operation.MULTIPLICATION)),
                        2));

        testMap.put("Equations, no doubles", new Quiz(
                new EquationTaskGenerator(0,
                        42,
                        EnumSet.of(MathTask.Operation.ADDITION,
                                MathTask.Operation.SUBSTRACTION)),
                        5));

        testMap.put("Expressions, with doubles", new Quiz(
                new ExpressionTaskGenerator(0,
                        42,
                        EnumSet.of(MathTask.Operation.ADDITION,
                                MathTask.Operation.SUBSTRACTION,
                                MathTask.Operation.MULTIPLICATION,
                                MathTask.Operation.DIVISION)),
                        5));

        testMap.put("Equations, with doubles", new Quiz(
                new EquationTaskGenerator(0,
                        42,
                        EnumSet.of(MathTask.Operation.ADDITION,
                                MathTask.Operation.SUBSTRACTION,
                                MathTask.Operation.MULTIPLICATION,
                                MathTask.Operation.DIVISION)),
                        5));

        testMap.put("GroupTaskGenerator", new Quiz(
                new GroupTaskGenerator(
                        new PoolTaskGenerator(false,
                                new TextTask("Как дела?", "норм"),
                                new TextTask("Сколько всего дней", "много")),
                        new EquationTaskGenerator(0, 10,
                                EnumSet.of(MathTask.Operation.ADDITION)),
                        new ExpressionTaskGenerator(0, 10,
                                EnumSet.of(MathTask.Operation.ADDITION))),
                        5));

        return testMap;
    }
}