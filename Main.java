import by.request3301.quizer.Quiz;
import by.request3301.quizer.Result;
import by.request3301.quizer.Task;
import by.request3301.quizer.generators.GroupTaskGenerator;
import by.request3301.quizer.generators.PoolTaskGenerator;
import by.request3301.quizer.generators.math.EquationMathTaskGenerator;
import by.request3301.quizer.generators.math.ExpressionMathTaskGenerator;
import by.request3301.quizer.tasks.TextTask;
import by.request3301.quizer.tasks.math.MathTask.Operation;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    static Map<String, Quiz> getQuizMap() {
        Map<String, Quiz> quizMap = new HashMap<>();

        ExpressionMathTaskGenerator expressionTaskGenerator = new ExpressionMathTaskGenerator(
                10, 100, EnumSet.allOf(Operation.class)
        );
        Quiz expressionQuiz = new Quiz(expressionTaskGenerator, 10);
        quizMap.put("expression", expressionQuiz);

        EquationMathTaskGenerator equationTaskGenerator = new EquationMathTaskGenerator(
                10, 100, EnumSet.allOf(Operation.class)
        );
        Quiz equationQuiz = new Quiz(equationTaskGenerator, 10);
        quizMap.put("equation", equationQuiz);

        TextTask textTask1 = new TextTask("The square side's lenght is 4. What is it's area?", "16");
        TextTask textTask2 = new TextTask("Melody has 5 apples. She gave 3 apples to Killua. " +
                "How many apples does Melody have now?", "2");
        PoolTaskGenerator<TextTask> poolTaskGenerator = new PoolTaskGenerator<>(
                false, textTask1, textTask2
        );
        Quiz poolQuiz = new Quiz(poolTaskGenerator, 2);
        quizMap.put("pool", poolQuiz);

        GroupTaskGenerator<? extends Task> groupTaskGenerator = new GroupTaskGenerator<>(
                expressionTaskGenerator,
                equationTaskGenerator,
                poolTaskGenerator
        );
        Quiz groupQuiz = new Quiz(groupTaskGenerator, 15);
        quizMap.put("group", groupQuiz);

        return quizMap;
    }

    public static void main(String[] args) {
        Map<String, Quiz> quizMap = getQuizMap();
        Scanner scanner = new Scanner(System.in);

        String quizName;
        while (true) {
            System.out.println("Select the quiz");
            quizName = scanner.nextLine();
            if (quizMap.containsKey(quizName)) {
                break;
            }
            System.out.println("No such quiz");
        }
        Quiz quiz = quizMap.get(quizName);
        System.out.println("The quiz starts now!");
        while (!quiz.isFinished()) {
            Task task = quiz.nextTask();
            System.out.println(task.getText());
            String answer = scanner.nextLine();
            Result result = quiz.provideAnswer(answer);
            System.out.println(result);
        }
        System.out.println("Quiz finished!");
        System.out.println("Your score: " + quiz.getMark());
        scanner.close();
    }
}