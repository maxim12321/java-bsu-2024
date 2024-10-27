package by.TimurTahil.quizer;

import by.TimurTahil.quizer.generators.GroupTaskGenerator;
import by.TimurTahil.quizer.generators.PoolTaskGenerator;
import by.TimurTahil.quizer.generators.TextTaskGenerator;
import by.TimurTahil.quizer.generators.math.EquationTaskGenerator;
import by.TimurTahil.quizer.generators.math.ExpressionTaskGenerator;
import by.TimurTahil.quizer.tasks.TextTask;

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
        map.put("Легкие выражения", new Quiz(
                new ExpressionTaskGenerator(0, 10), 10));
        map.put("Средние выражения", new Quiz(
                new ExpressionTaskGenerator(0, 100), 10));
        map.put("Тяжелые выражения", new Quiz(
                new ExpressionTaskGenerator(0, 1000), 10));
        map.put("Легкие уравнения", new Quiz(
                new EquationTaskGenerator(0, 10), 10));
        map.put("Средние уравнения", new Quiz(
                new EquationTaskGenerator(0, 100), 10));
        map.put("Тяжелые уравнения", new Quiz(
                new EquationTaskGenerator(0, 1000), 10));
        map.put("Столицы", new Quiz(
                new PoolTaskGenerator(
                        false,
                        new TextTask("Столица Великобритании?", "Лондон"),
                        new TextTask("Столица Франции?", "Париж"),
                        new TextTask("Столица России?", "Москва"),
                        new TextTask("Столица Беларуси?", "Минск"),
                        new TextTask("Столица Польши?", "Варшава"),
                        new TextTask("Столица Украины?", "Киев")),
                6));
        map.put("GroupTask", new Quiz(
                new GroupTaskGenerator(
                        new PoolTaskGenerator(
                                false,
                                new TextTask("Столица Великобритании?", "Лондон"),
                                new TextTask("Столица Франции?", "Париж"),
                                new TextTask("Столица России?", "Москва"),
                                new TextTask("Столица Беларуси?", "Минск"),
                                new TextTask("Столица Польши?", "Варшава"),
                                new TextTask("Столица Украины?", "Киев")),
                        new ExpressionTaskGenerator(0, 100)),
                10));

        return map;
    }

    public static void main(String[] args) {
        Map<String, Quiz> quizMap = getQuizMap();
        System.out.println("Доступные тесты: ");
        quizMap.keySet().forEach(System.out::println);
        System.out.println("Введите название теста...");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while (!quizMap.containsKey(input)) {
            System.out.println("Тест не найден. Введите название теста...");
            input = scanner.nextLine();
        }
        Quiz quiz = quizMap.get(input);

        System.out.println("Введите ваши ответы на следующие вопросы: ");
        while (!quiz.isFinished()) {
            System.out.println(quiz.nextTask().getText());
            String answer = scanner.nextLine();
            Result result = quiz.provideAnswer(answer);
            System.out.println(switch (result) {
                case OK -> "Ответ верный.";
                case WRONG -> "Ответ неверный.";
                case INCORRECT_INPUT -> "Некорректный ввод.";
            });
        }
        System.out.println("Ваша оценка: " + quiz.getMark());
    }
}