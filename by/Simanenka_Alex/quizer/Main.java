package by.Simanenka_Alex.quizer;


import by.Simanenka_Alex.quizer.Result;
import by.Simanenka_Alex.quizer.Task;
import by.Simanenka_Alex.quizer.Quiz;
import by.Simanenka_Alex.quizer.TaskGenerator;
import by.Simanenka_Alex.quizer.generators.*;
import by.Simanenka_Alex.quizer.tasks.MathTask;
import by.Simanenka_Alex.quizer.tasks.TextTask;


import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    /**
     * @return тесты в {@link Map}, где
     * ключ     - название теста {@link String}
     * значение - сам тест       {@link //Quiz}
     */
    static Map<String, Quiz> getQuizMap() {
        HashMap<String, Quiz> map = new HashMap<>();
        map.put("A", new Quiz(
                new ExpressionTaskGenerator(
                        0,
                        10,
                        EnumSet.of(MathTask.Operation.SUB, MathTask.Operation.ADD, MathTask.Operation.DIV, MathTask.Operation.MUL)
                ), 5)
        );

        map.put("B", new Quiz(
                new EquationTaskGenerator(
                        0,
                        10,
                        EnumSet.of(MathTask.Operation.SUB, MathTask.Operation.ADD, MathTask.Operation.DIV, MathTask.Operation.MUL)
                ), 5)
        );

        map.put("C", new Quiz(
                new GroupTaskGenerator(
                new EquationTaskGenerator(
                        0,
                        10,
                        EnumSet.of(MathTask.Operation.SUB, MathTask.Operation.ADD, MathTask.Operation.DIV, MathTask.Operation.MUL)
                ),
                new ExpressionTaskGenerator(
                        0,
                        10,
                        EnumSet.of(MathTask.Operation.SUB, MathTask.Operation.ADD, MathTask.Operation.DIV, MathTask.Operation.MUL)
                )
                ), 5)
        );

        map.put("D", new Quiz(
                new PoolTaskGenerator(
                        true,
                        new TextTask("Concat a+b=?", "ab"),
                        new TextTask("Concat b+b=?", "bb"),
                        new TextTask("Concat d+b=?", "db"),
                        new TextTask("Concat t+b=?", "tb"),
                        new TextTask("Concat t+t=?", "tt")
                ), 5
        ));
        map.put("E", new Quiz(
                new ArithmeticMeanTaskGenerator(
                        1,
                        10
                ), 5
        ));

        return map;
    }

    public static void main(String[] args) {
        Map<String, Quiz> map = getQuizMap();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Write the name of the test");
        String name = scanner.nextLine();
        while (!map.containsKey(name)) {
            System.out.println("Try again.");
            name = scanner.nextLine();
        }
        Quiz quiz = map.get(name);
        while (!quiz.isFinished()) {
            System.out.println(quiz.nextTask().getText());
            String answer = scanner.nextLine();
            System.out.println(quiz.provideAnswer(answer));
        }
        if (quiz.getMark() < 0.4) {
            System.out.println("Pupupu... Maybe next time? :(");
        }
        else {
            System.out.println("Not bad! You passed the test! :)");
        }
    }
}
