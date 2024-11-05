package by.merinovvvv.quizer;

import by.merinovvvv.quizer.exceptions.QuizFinishedException;
import by.merinovvvv.quizer.exceptions.QuizNotFinishedException;
import by.merinovvvv.quizer.generators.*;
import by.merinovvvv.quizer.tasks.AppleTask;
import by.merinovvvv.quizer.tasks.EquationTask;
import by.merinovvvv.quizer.tasks.ExpressionTask;
import by.merinovvvv.quizer.tasks.TextTask;
import by.merinovvvv.quizer.tasks.math.MathTask;

import java.util.*;

public class Main {


    public static void main(String[] args) {
        try {
            Map<String, Quiz> quizMap = getQuizMap();
            Scanner sc = new Scanner(System.in);
            String input = "";
            Quiz quiz = null;

            while (input.isEmpty() || quiz == null) {
                System.out.println("Enter the name of the test...");
                input = sc.nextLine().trim();
                quiz = quizMap.get(input);
                if (quiz == null) {
                    System.out.println("Test not found. Try again.");
                }
            }

            while (!quiz.isFinished()) {
                System.out.println(quiz.nextTask().getText());
                System.out.println("Input the answer: ");
                String answer = sc.nextLine().trim();
                Result res = quiz.provideAnswer(answer);
                if (res == Result.OK) {
                    System.out.println("Correct!");
                } else if (res == Result.WRONG) {
                    System.out.println("Wrong!");
                } else {
                    System.out.println("Incorrect input!");
                }
            }
            System.out.println("Test is finished. The results are:");
            System.out.println(quiz.getCorrectAnswerNumber() + " correct answers;");
            System.out.println(quiz.getWrongAnswerNumber() + " wrong answers;");
            System.out.println(quiz.getIncorrectInputNumber() + " incorrect inputs.");
            System.out.println("Your final mark is - " + quiz.getMark());
        } catch (QuizNotFinishedException e) {
            System.out.println("Error while getting mark: " + e.getMessage());
            System.exit(1);
        } catch (IllegalArgumentException e) {
            System.out.println("Error while creating quizzes: " + e.getMessage());
            System.exit(1);
        } catch (QuizFinishedException e) {
            System.out.println("Error while getting task: " + e.getMessage());
            System.exit(1);
        }
    }

    /**
     * @return тесты в {@link Map}, где
     * ключ - название теста {@link String}
     * значение - сам тест       {@link Quiz}
     */
    static Map<String, Quiz> getQuizMap() {

        Map<String, Quiz> quizMap = new HashMap<>(Map.of());
        MathTask.Operation addOperation = MathTask.Operation.ADDITION;
        MathTask.Operation subOperation = MathTask.Operation.SUBTRACTION;
        MathTask.Operation mulOperation = MathTask.Operation.MULTIPLICATION;
        MathTask.Operation divOperation = MathTask.Operation.DIVISION;

        try {

            //ExpressionTaskGenerators and EquationTaskGenerators use
            TaskGenerator<MathTask> expressionTaskGeneratorSum = new ExpressionTaskGenerator(1, 100, addOperation);
            TaskGenerator<MathTask> equationTaskGeneratorSum = new EquationTaskGenerator(1, 28, addOperation);
            quizMap.put("expression sum", new Quiz(expressionTaskGeneratorSum, 10));
            quizMap.put("equation sum", new Quiz(equationTaskGeneratorSum, 10));

            TaskGenerator<MathTask> expressionTaskGeneratorDiff = new ExpressionTaskGenerator(1, 100, subOperation);
            TaskGenerator<MathTask> equationTaskGeneratorDiff = new EquationTaskGenerator(1, 28, subOperation);
            quizMap.put("expression difference", new Quiz(expressionTaskGeneratorDiff, 10));
            quizMap.put("equation difference", new Quiz(equationTaskGeneratorDiff, 10));

            TaskGenerator<MathTask> expressionTaskGeneratorMulti = new ExpressionTaskGenerator(1, 100, mulOperation);
            TaskGenerator<MathTask> equationTaskGeneratorMulti = new EquationTaskGenerator(1, 28, mulOperation);
            quizMap.put("expression multiplication", new Quiz(expressionTaskGeneratorMulti, 10));
            quizMap.put("equation multiplication", new Quiz(equationTaskGeneratorMulti, 10));

            TaskGenerator<MathTask> expressionTaskGeneratorDiv = new ExpressionTaskGenerator(1, 100, divOperation);
            TaskGenerator<MathTask> equationTaskGeneratorDiv = new EquationTaskGenerator(1, 28, divOperation);
            quizMap.put("expression division", new Quiz(expressionTaskGeneratorDiv, 10));
            quizMap.put("equation division", new Quiz(equationTaskGeneratorDiv, 10));

            //GroupTaskGenerator use

            TaskGenerator<MathTask> groupExpressionTaskGenerator = new GroupTaskGenerator<>(expressionTaskGeneratorSum, expressionTaskGeneratorDiff, expressionTaskGeneratorMulti, expressionTaskGeneratorDiv);
            TaskGenerator<MathTask> groupEquationTaskGenerator = new GroupTaskGenerator<>(equationTaskGeneratorSum, equationTaskGeneratorDiff, equationTaskGeneratorMulti, equationTaskGeneratorDiv);
            quizMap.put("group expression", new Quiz(groupExpressionTaskGenerator, 10));
            quizMap.put("group equation", new Quiz(groupEquationTaskGenerator, 10));

            //GroupTaskGenerator with lists use

            List<TaskGenerator<MathTask>> expressionGeneratorList = new ArrayList<>();
            List<TaskGenerator<MathTask>> equationGeneratorList = new ArrayList<>();

            expressionGeneratorList.add(expressionTaskGeneratorSum);
            expressionGeneratorList.add(expressionTaskGeneratorDiff);
            expressionGeneratorList.add(expressionTaskGeneratorMulti);
            expressionGeneratorList.add(expressionTaskGeneratorDiv);
            TaskGenerator<MathTask> groupExpressionTaskGeneratorListed = new GroupTaskGenerator<>(expressionGeneratorList);
            quizMap.put("group expression 2", new Quiz(groupExpressionTaskGeneratorListed, 10));

            equationGeneratorList.add(equationTaskGeneratorSum);
            equationGeneratorList.add(equationTaskGeneratorDiff);
            equationGeneratorList.add(equationTaskGeneratorMulti);
            equationGeneratorList.add(equationTaskGeneratorDiv);
            TaskGenerator<MathTask> groupEquationTaskGeneratorListed = new GroupTaskGenerator<>(equationGeneratorList);
            quizMap.put("group equation 2", new Quiz(groupEquationTaskGeneratorListed, 10));

            //PoolTaskGenerator use
            TaskGenerator<MathTask> poolExpressionTaskGenerator = new PoolTaskGenerator<>(true,
                    new ExpressionTask(12, 28, "+"),
                    new ExpressionTask(12, 28, "-"),
                    new ExpressionTask(12, 28, "*"),
                    new ExpressionTask(12, 28, "/")
            );
            quizMap.put("pool expression", new Quiz(poolExpressionTaskGenerator, 10));

            TaskGenerator<MathTask> poolEquationTaskGenerator = new PoolTaskGenerator<>(true,
                    new EquationTask(12, 28,  "+", 54),
                    new EquationTask(12, 28, "-", 54),
                    new EquationTask(12, 28, "*", 54),
                    new EquationTask(12, 28, "/", 54)
            );
            quizMap.put("pool equation", new Quiz(poolEquationTaskGenerator, 10));

            //Capital cities quiz
            PoolTaskGenerator<Task> capitalsTasks = getCapitalsTaskGenerator();
            quizMap.put("capitals", new Quiz(capitalsTasks, 6));

            //Apple tasks
            TaskGenerator<AppleTask> AppleTaskGenerator = new AppleTaskGenerator(1, 100);
            quizMap.put("apples", new Quiz(AppleTaskGenerator, 10));


        } catch (IllegalArgumentException e) {
            System.out.println("Error while creating quizzes: " + e.getMessage());
            System.exit(1);
        }
        return quizMap;
    }

    static PoolTaskGenerator<Task> getCapitalsTaskGenerator() {
        List<Task> textTaskList = new ArrayList<>();
        Task capitalTask1 = new TextTask("What is the capital of Belarus?", "Minsk");
        Task capitalTask2 = new TextTask("What is the capital of Russia?", "Moscow");
        Task capitalTask3 = new TextTask("What is the capital of Ukraine?", "Kyiv");
        Task capitalTask4 = new TextTask("What is the capital of Poland?", "Warsaw");
        Task capitalTask5 = new TextTask("What is the capital of Germany?", "Berlin");
        Task capitalTask6 = new TextTask("What is the capital of France?", "Paris");
        Task capitalTask7 = new TextTask("What is the capital of Spain?", "Madrid");
        Task capitalTask8 = new TextTask("What is the capital of Italy?", "Rome");

        textTaskList.add(capitalTask1);
        textTaskList.add(capitalTask2);
        textTaskList.add(capitalTask3);
        textTaskList.add(capitalTask4);
        textTaskList.add(capitalTask5);
        textTaskList.add(capitalTask6);
        textTaskList.add(capitalTask7);
        textTaskList.add(capitalTask8);
        return new PoolTaskGenerator<>(false, textTaskList);
    }
}
