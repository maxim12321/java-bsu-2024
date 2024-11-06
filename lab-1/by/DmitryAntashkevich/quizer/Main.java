package by.DmitryAntashkevich.quizer;

import by.DmitryAntashkevich.quizer.generators.GroupTaskGenerator;
import by.DmitryAntashkevich.quizer.generators.PoolTaskGenerator;
import by.DmitryAntashkevich.quizer.generators.math.EquationTaskGenerator;
import by.DmitryAntashkevich.quizer.generators.math.ExpressionTaskGenerator;
import by.DmitryAntashkevich.quizer.tasks.math.MathTask.Operation;
import by.DmitryAntashkevich.quizer.tasks.*;

import java.util.*;

public class Main {
    /**
     * @return тесты в {@link Map}, где
     * ключ - название теста {@link String}
     * значение - сам тест {@link Quiz}
     */
    static Map<String, Quiz> getQuizMap() {
        Map<String, Quiz> quizMap = new HashMap<>();

        // Math generators
        var simpleExpressionGenerator = new ExpressionTaskGenerator(0, 100, EnumSet.of(Operation.ADDITION, Operation.SUBTRACTION));
        var advancedExpressionGenerator = new ExpressionTaskGenerator(-100, 100, EnumSet.of(Operation.MULTIPLICATION, Operation.DIVISION));
        var simpleEquationGenerator = new EquationTaskGenerator(0, 100, EnumSet.of(Operation.ADDITION, Operation.SUBTRACTION));
        var advancedEquationGenerator = new EquationTaskGenerator(-100, 100, EnumSet.of(Operation.MULTIPLICATION, Operation.DIVISION));

        quizMap.put("simple expr", new Quiz(simpleExpressionGenerator, 5));
        quizMap.put("advanced expr", new Quiz(advancedExpressionGenerator, 10));
        quizMap.put("simple eq", new Quiz(simpleEquationGenerator, 5));
        quizMap.put("advanced eq", new Quiz(advancedEquationGenerator, 10));

        // Text tasks and pool generators
        Task textTask1 = new TextTask("Какой ответ на главный вопрос жизни, вселенной и вообще?", "42");
        Task textTask2 = new TextTask("Кто создал язык программирования c++?", "Бьёрн Страуструп");
        Task textTask3 = new TextTask("Как совмещать уник, шад и личную жизнь?", "Никак");

        var poolGenerator = new PoolTaskGenerator<>(true, textTask1, textTask2, textTask3);

        quizMap.put("pool crush", new Quiz(new PoolTaskGenerator<>(false, textTask1, textTask2, textTask3), 5));
        quizMap.put("pool no dup", new Quiz(new PoolTaskGenerator<>(false, textTask1, textTask2, textTask3), 3));
        quizMap.put("pool with dup", new Quiz(poolGenerator, 5));

        // Group generators
        var groupCrushGenerator = new GroupTaskGenerator<>(new PoolTaskGenerator<>(false, textTask1, textTask2, textTask3), new PoolTaskGenerator<>(false, textTask1, textTask2, textTask3));
        var allInOneGenerator = new GroupTaskGenerator<>(simpleEquationGenerator, advancedEquationGenerator, simpleExpressionGenerator, advancedExpressionGenerator, poolGenerator);

        quizMap.put("group crush", new Quiz(groupCrushGenerator, 7));
        quizMap.put("all in one", new Quiz(allInOneGenerator, 9));

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
            final String answer = in.nextLine();
            System.out.println(switch (quiz.provideAnswer(answer)) {
                case OK -> "Верный ответ!";
                case WRONG -> "Неверный ответ!";
                case INCORRECT_INPUT -> "Некорректный формат ответа. Попробуйте еще раз...";
            });
        }
        System.out.printf("Ваша оценка: %.2f\n", quiz.getMark());
    }
}
