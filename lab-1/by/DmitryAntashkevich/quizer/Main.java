package by.DmitryAntashkevich.quizer;

import by.DmitryAntashkevich.quizer.generators.math.EquationTaskGenerator;
import by.DmitryAntashkevich.quizer.generators.math.ExpressionTaskGenerator;
import by.DmitryAntashkevich.quizer.tasks.math.MathTask.Operation;

import java.util.*;

public class Main {
    /**
     * @return тесты в {@link Map}, где
     * ключ - название теста {@link String}
     * значение - сам тест       {@link Quiz}
     */
    static Map<String, Quiz> getQuizMap() {
        Map<String, Quiz> quizMap = new HashMap<>();

        // Math generators
        TaskGenerator simpleExpressionGenerator = new ExpressionTaskGenerator(0, 100, EnumSet.of(Operation.ADDITION, Operation.SUBTRACTION));
        TaskGenerator advancedExpressionGenerator = new ExpressionTaskGenerator(-100, 100, EnumSet.of(Operation.MULTIPLICATION, Operation.DIVISION));
        TaskGenerator simpleEquationGenerator = new EquationTaskGenerator(0, 100, EnumSet.of(Operation.ADDITION, Operation.SUBTRACTION));
        TaskGenerator advancedEquationGenerator = new EquationTaskGenerator(-100, 100, EnumSet.of(Operation.MULTIPLICATION, Operation.DIVISION));

        quizMap.put("simple expr", new Quiz(simpleExpressionGenerator, 5));
        quizMap.put("advanced expr", new Quiz(advancedExpressionGenerator, 10));
        quizMap.put("simple eq", new Quiz(simpleEquationGenerator, 5));
        quizMap.put("advanced eq", new Quiz(advancedEquationGenerator, 10));

        return quizMap;
    }

    public static void main(String[] args) {
        var quizMap = getQuizMap();
        System.out.println("Доступные тесты:");
        for (String key : quizMap.keySet().stream().sorted().toList()) {
            System.out.println(key);
        }

        System.out.println("Введите название теста...");
        Scanner in = new Scanner(System.in);
        String quizName;
        while (true) {
            quizName = in.nextLine();
            if (quizMap.containsKey(quizName)) {
                break;
            }
            System.out.println("Теста с данным названием не существует. Введите название теста из списка выше...");
        }
        Quiz quiz = quizMap.get(quizName);

        while (!quiz.isFinished()) {
            Task task = quiz.nextTask();
            System.out.println(task.getText());
            final String answer = in.next();
            System.out.println(switch (quiz.provideAnswer(answer)) {
                case OK -> "Верный ответ!";
                case WRONG -> "Неверный ответ!";
                case INCORRECT_INPUT -> "Некорректный формат ответа. Попробуйте еще раз...";
            });
        }
        System.out.printf("Ваша оценка: %.2f\n", quiz.getMark());
    }
}
