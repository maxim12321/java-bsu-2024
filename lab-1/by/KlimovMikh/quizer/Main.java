package KlimovMikh.quizer;

import KlimovMikh.quizer.generators.math.EquationTaskGenerator;
import KlimovMikh.quizer.generators.math.ExpressionTaskGenerator;
import KlimovMikh.quizer.generators.GroupTaskGenerator;
import KlimovMikh.quizer.generators.PoolTaskGenerator;
import KlimovMikh.quizer.tasks.TextTask;
import KlimovMikh.quizer.tasks.math.AbstractMathTask;
import KlimovMikh.quizer.tasks.math.EquationTask;
import KlimovMikh.quizer.tasks.math.ExpressionTask;
import KlimovMikh.quizer.tasks.math.MathTask;

import java.util.*;

public class Main {
    /**
     * @return тесты в {@link Map}, где
     * ключ - название теста {@link String}
     * значение - сам тест       {@link Quiz}
     */
    static Map<String, Quiz> getQuizMap() {
        HashMap<String, Quiz> result = new HashMap<>();

        EnumSet<MathTask.Operation> operations = EnumSet.allOf(MathTask.Operation.class);

        ExpressionTask task = new ExpressionTask(1, 10, MathTask.Operation.MINUS);
        EquationTask task2 = new EquationTask(7, 50, MathTask.Operation.PLUS, false);
        TextTask textTask = new TextTask("Paul received 2 apples from John and 3 apples from Molly. How many apples " +
                "does Paul have?", "5");

        ExpressionTaskGenerator expressionTaskGenerator = new ExpressionTaskGenerator(-100, 100, operations);
        EquationTaskGenerator equationTaskGenerator = new EquationTaskGenerator(-100, 100, operations);
        PoolTaskGenerator<TextTask> textPool = new PoolTaskGenerator<>(false, textTask);
        PoolTaskGenerator<Task> unitedPool = new PoolTaskGenerator<>(false, task, task2, textTask);
        PoolTaskGenerator<AbstractMathTask> mathPool = new PoolTaskGenerator<>(false, task, task2);
        PoolTaskGenerator<AbstractMathTask> mathPoolDup = new PoolTaskGenerator<>(true, task, task2);
        GroupTaskGenerator groupTaskGeneratorGood = new GroupTaskGenerator(expressionTaskGenerator, equationTaskGenerator, mathPoolDup);
        GroupTaskGenerator groupTaskGeneratorBad = new GroupTaskGenerator(expressionTaskGenerator, equationTaskGenerator, mathPool);

        Quiz qExpressions = new Quiz(expressionTaskGenerator, 3);
        Quiz qEquations = new Quiz(equationTaskGenerator, 10);
        Quiz qPoolUnited = new Quiz(unitedPool, 3);
        Quiz qPoolText = new Quiz(textPool, 1);
        Quiz qPoolBad = new Quiz(textPool, 10);
        Quiz qPoolMath = new Quiz(mathPool, 2);
        Quiz qPoolMathGood = new Quiz(mathPoolDup, 10);
        Quiz qGroupsGood = new Quiz(groupTaskGeneratorGood, 6);
        Quiz qGroupsBad = new Quiz(groupTaskGeneratorBad, 10);

        result.put("expressions", qExpressions);
        result.put("equations", qEquations);
        result.put("text pool", qPoolText);
        result.put("math pool", qPoolMath);
        result.put("united pool", qPoolUnited);
        result.put("bad pool", qPoolBad);
        result.put("good pool", qPoolMathGood);
        result.put("good groups", qGroupsGood);
        result.put("bad groups", qGroupsBad);
        return result;
    }

    public static void main(String[] args) {
        System.out.print("Hello and welcome! ");
        Map<String, Quiz> quizMap = getQuizMap();
        System.out.print("Enter the name of the quiz: ");
        Scanner scanner = new Scanner(System.in);
        String quizName = scanner.nextLine();
        while (!quizMap.containsKey(quizName)) {
            System.out.print("Quiz with this name does not exist. Enter the name of the quiz: ");
            quizName = scanner.nextLine();
        }
        Quiz quiz = quizMap.get(quizName);
        while (!quiz.isFinished()) {
            Task task;
            try {
                task = quiz.nextTask();
            } catch (IllegalStateException e) {
                System.out.println("Exiting the quiz...");
                break;
            }
            System.out.println(task.getText());
            String answer = scanner.nextLine();
            Result result = quiz.provideAnswer(answer);
            System.out.println(result);
        }
        System.out.println("Result: ");
        System.out.printf("%.2f", quiz.getMark());
    }
}