package by.AlexAgeev.quizer;

import by.AlexAgeev.quizer.generators.GroupTaskGenerator;
import by.AlexAgeev.quizer.generators.PoolTaskGenerator;
import by.AlexAgeev.quizer.generators.TextTaskGenerator;
import by.AlexAgeev.quizer.generators.math.EquationTaskGenerator;
import by.AlexAgeev.quizer.generators.math.ExpressionTaskGenerator;
import by.AlexAgeev.quizer.tasks.TextTask;
import by.AlexAgeev.quizer.tasks.math.MathTask;

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
        map.put("Expressions Easy", new Quiz(
                new ExpressionTaskGenerator(0, 10), 10));
        map.put("Expressions Medium", new Quiz(
                new ExpressionTaskGenerator(0, 100), 10));
        map.put("Expressions Hard", new Quiz(
                new ExpressionTaskGenerator(0, 1000), 10));
        map.put("Expressions Unreal", new Quiz(
                new ExpressionTaskGenerator(0, 65535), 10));
        map.put("Equations Easy", new Quiz(
                new EquationTaskGenerator(0, 10), 10));
        map.put("Equations Medium", new Quiz(
                new EquationTaskGenerator(0, 100), 10));
        map.put("Equations Hard", new Quiz(
                new EquationTaskGenerator(0, 1000), 10));
        map.put("Capitals with duplicates", new Quiz(
                new PoolTaskGenerator(
                        true,
                        new TextTask("Столица Великобритании?", "Лондон"),
                        new TextTask("Столица Ирландии?", "Дублин"),
                        new TextTask("Столица России?", "Москва"),
                        new TextTask("Столица Беларуси?", "Минск"),
                        new TextTask("Столица Канады?", "Оттава"),
                        new TextTask("Столица Франции?", "Париж"),
                        new TextTask("Столица Польши?", "Варшава"),
                        new TextTask("Столица Украины?", "Киев"),
                        new TextTask("Столица Польши?", "Варшава"),
                        new TextTask("Столица Чехии?", "Прага"),
                        new TextTask("Столица Молдовы?", "Кишинёв"),
                        new TextTask("Столица Болгарии?", "София")),
                5));
        map.put("Capitals without duplicates", new Quiz(
                new PoolTaskGenerator(
                        false,
                        new TextTask("Столица Великобритании?", "Лондон"),
                        new TextTask("Столица Ирландии?", "Дублин"),
                        new TextTask("Столица России?", "Москва"),
                        new TextTask("Столица Беларуси?", "Минск"),
                        new TextTask("Столица Канады?", "Оттава"),
                        new TextTask("Столица Франции?", "Париж"),
                        new TextTask("Столица Польши?", "Варшава"),
                        new TextTask("Столица Украины?", "Киев"),
                        new TextTask("Столица Польши?", "Варшава"),
                        new TextTask("Столица Чехии?", "Прага"),
                        new TextTask("Столица Молдовы?", "Кишинёв"),
                        new TextTask("Столица Болгарии?", "София")),
                5));
        map.put("PoolTask without duplicates and no fail", new Quiz(
                new PoolTaskGenerator(
                        false,
                        new TextTask("Цвет таблетки для искусственной реальности?", "Синий"),
                        new TextTask("Цвет таблетки для реального мира?", "Красный")),
                2));
        map.put("PoolTask without duplicates and fail", new Quiz(
                new PoolTaskGenerator(
                        false,
                        new TextTask("Цвет таблетки для искусственной реальности?", "Синий"),
                        new TextTask("Цвет таблетки для реального мира?", "Красный"),
                        new TextTask("Цвет зелёной таблетки?", "Зелёный")),
                4));
        map.put("GroupTask and fail", new Quiz(
                new GroupTaskGenerator(
                        new PoolTaskGenerator(
                                false,
                                new TextTask("Какого цвета (Оранжевый) апельсин?", "Оранжевый"),
                                new TextTask("Какого цвета (Красный) яблоко?", "Красный")),
                        new PoolTaskGenerator(
                                false,
                                new TextTask("Столица Франции?", "Париж"),
                                new TextTask("Как зовут Беларуси?", "Минск"))),
                5));


        map.put("Different type tasks fail", new Quiz(
                new GroupTaskGenerator(
                        new PoolTaskGenerator(
                                false,
                                new TextTask("Столица Мексики?", "Мехико"),
                                new TextTask("Столица Турции?", "Анкара")),
                        new ExpressionTaskGenerator(0, 10)),
                10));

        map.put("TextTask", new Quiz(
                new TextTaskGenerator(5, 100),
                5));
        map.put("Min is bigger than max throws exception", new Quiz(
                new EquationTaskGenerator(2, 1),
                10));
        return map;
    }

    public static void main(String[] args) {
        Map<String, Quiz> quiz = getQuizMap();
        System.out.println("Доступные тесты:");
        quiz.keySet().forEach(System.out::println);
        System.out.println("Введите название теста:");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while (!quiz.containsKey(input)) {
            System.out.println("Такого теста не существует. Введите название теста...");
            input = scanner.nextLine();
        }
        Quiz quizNow = quiz.get(input);

        System.out.println("Пожалуйста, введите ваши ответы на следующие вопросы");
        System.out.println("Если ответом на задание является слово, то оно должно начинаться с большой буквы!");
        while (!quizNow.isFinished()) {
            System.out.println(quizNow.nextTask().getText());
            String answer = scanner.nextLine();
            Result result = quizNow.provideAnswer(answer);
            System.out.println(switch (result) {
                case OK -> "Ответ верный";
                case WRONG -> "Ответ неверный";
                case INCORRECT_INPUT -> "Некорректный ввод!";
            });
        }
        scanner.close();
        System.out.println("Тест успешно пройден, ваша оценка: " + quizNow.getMark());
    }
}