package by.AlbertRadoshko.quizer;

import by.AlbertRadoshko.quizer.generators.GroupTaskGenerator;
import by.AlbertRadoshko.quizer.generators.PoolTaskGenerator;
import by.AlbertRadoshko.quizer.generators.math.EquationTaskGenerator;
import by.AlbertRadoshko.quizer.generators.math.ExpressionTaskGenerator;
import by.AlbertRadoshko.quizer.tasks.TextTask;
import by.AlbertRadoshko.quizer.tasks.math.MathTask;

import java.util.*;

public class Main {
    /**
     * @return тесты в {@link Map}, где
     * ключ - название теста {@link String}
     * значение - сам тест       {@link Quiz}
     */
    static Map<String, Quiz> getQuizMap() {
        Map<String, Quiz> res = new HashMap<>();

        // выражение с плюс/минус
        var exprGen1 = new ExpressionTaskGenerator(-20, 20, EnumSet.of(MathTask.Operation.ADDITION, MathTask.Operation.SUBTRACTION));
        // выражение с делить/умножить
        var exprGen2 = new ExpressionTaskGenerator(-50, 50, EnumSet.of(MathTask.Operation.MULTIPLICATION, MathTask.Operation.DIVISION));
        res.put("+- expr", new Quiz(exprGen1, 5));
        res.put("/* expr", new Quiz(exprGen2, 10));

        // уравнение с плюс/минус
        var eqGen1 = new EquationTaskGenerator(-20, 20, EnumSet.of(MathTask.Operation.ADDITION, MathTask.Operation.SUBTRACTION));
        // уравнение с делить/умножить
        var eqGen2 = new EquationTaskGenerator(-50, 50, EnumSet.of(MathTask.Operation.MULTIPLICATION, MathTask.Operation.DIVISION));
        res.put("+- eq", new Quiz(eqGen1, 5));
        res.put("/* eq", new Quiz(eqGen2, 10));

        // текстовые таски
        Task textTask1 = new TextTask("Столица Франции?", "Париж");
        Task textTask2 = new TextTask("Столица Италии?", "Рим");
        Task textTask3 = new TextTask("Столица Чехии?", "Прага");

        // генератор тасок из пула
        var poolGen = new PoolTaskGenerator(true, textTask1, textTask2, textTask3);
        res.put("pool gen exactly 3", new Quiz(new PoolTaskGenerator(false, textTask1, textTask2, textTask3), 3));
        res.put("pool gen 5 of 3", new Quiz(poolGen, 5));

        // группированный генератор
        var allInGen = new GroupTaskGenerator(eqGen1, eqGen2, exprGen1, exprGen2, poolGen);
        res.put("all in", new Quiz(allInGen, 9));
        return res;
    }

    public static void main(String[] args) {
        var map = getQuizMap();
        for (String key : map.keySet().stream().sorted().toList()) {
            System.out.println(key);
        }
        System.out.println("Введите название теста...");
        Scanner cin = new Scanner(System.in);
        String name;
        while (true) {
            name = cin.nextLine();
            if (map.containsKey(name)) {
                break;
            }
            System.out.println("Теста с таким названием нет, повторите попытку");
        }
        var quiz = map.get(name);
        while (!quiz.isFinished()) {
            var task = quiz.nextTask();
            System.out.println(task.getText());
            String ans = cin.nextLine();
            var validation_result = quiz.provideAnswer(ans);
            if (validation_result == Result.INCORRECT_INPUT) {
                System.out.println("Некорректный ввод, повторите попытку");
            } else {
                System.out.println("Ответ принят");
            }
            if (!quiz.isFinished()) {
                System.out.println("Переходим к следующему заданию");
                quiz.nextTask();
            }
        }
        System.out.printf("Итоговая оценка: %.2f\n", quiz.getMark());
    }
}
