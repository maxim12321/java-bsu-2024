package by.KuryanovichNikita.quizer;

import by.KuryanovichNikita.quizer.exceptions.NotValidGenerator;
import by.KuryanovichNikita.quizer.generators.*;
import by.KuryanovichNikita.quizer.tasks.Task;
import by.KuryanovichNikita.quizer.tasks.TextTask;
import by.KuryanovichNikita.quizer.tasks.math.MathTask;

import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    /**
     * @return тесты в {@link Map}, где
     * ключ - название теста {@link String}, значение - сам тест {@link Quiz}
     */
    static Map<String, Quiz> getQuizMap() {
        HashMap<String, Quiz> map = new HashMap<>();
        map.put("Addition", new Quiz(
                new ExpressionTaskGenerator(
                        0,
                        11,
                        EnumSet.of(MathTask.Operation.ADDITION)
                ), 5)
        );

        // Тест для вычитания
        map.put("Subtraction", new Quiz(
                new ExpressionTaskGenerator(
                        0,
                        21,
                        EnumSet.of(MathTask.Operation.SUBTRACTION)
                ), 5)
        );

        // Тест для умножения
        map.put("Multiplication", new Quiz(
                new ExpressionTaskGenerator(
                        1,
                        11,
                        EnumSet.of(MathTask.Operation.MULTIPLICATION)
                ), 5)
        );

        // Тест для деления
        map.put("Division", new Quiz(
                new ExpressionTaskGenerator(
                        1,
                        11,
                        EnumSet.of(MathTask.Operation.DIVISION)
                ), 5)
        );

        // Тест для смешанных операций
        map.put("Mixed Operations", new Quiz(
                new ExpressionTaskGenerator(
                        0,
                        11,
                        EnumSet.of(MathTask.Operation.ADDITION, MathTask.Operation.SUBTRACTION, MathTask.Operation.MULTIPLICATION, MathTask.Operation.DIVISION)
                ), 10)
        );

        // Тест для деления на ноль
        map.put("Division by Zero", new Quiz(
                new ExpressionTaskGenerator(
                        0,
                        11,
                        EnumSet.of(MathTask.Operation.DIVISION)
                ), 5)
        );


        // Тесты для EquationTaskGenerator
        map.put("Equation Addition", new Quiz(
                new EquationTaskGenerator(
                        0,
                        11,
                        EnumSet.of(MathTask.Operation.ADDITION)
                ), 5)
        );


        TextTask text1 = new TextTask("Как называется число, которое делится только на себя и на 1?: ","Простое");
        TextTask text2 = new TextTask("Посчитайте:   ∫e^(-x)^2 dx по промежутку [-∞, +∞): ", "√π");
        TextTask text3 = new TextTask("1 это простое число?: ","Нет");
        TextTask text4 = new TextTask("Как называется данная последовательность чисел: 1, 1, 2, 3, 5, 8, ... : ","Числа Фибоначчи");
        TextTask text5 = new TextTask("найдите сумму бесконечного ряда Σ 1/(n^2): ","(π^2)/6");
        TextTask text6 = new TextTask("Столица Чехии?", "Прага");
        TextTask text7 = new TextTask("Победитель ЧМ ао футболу 2022?", "Аргентина");

        map.put("Pool Text Tasks Without Repetitions",new Quiz(
                new PoolTaskGenerator(false,text1, text2, text3, text4, text5, text6, text7),6));

        map.put("Pool Text Tasks With Repetitions",new Quiz(
                new PoolTaskGenerator(true,text1, text2, text3, text4, text5, text6, text7),6));


        map.put("Equation Subtraction", new Quiz(
                new EquationTaskGenerator(
                        0,
                        21,
                        EnumSet.of(MathTask.Operation.SUBTRACTION)
                ), 5)
        );

        map.put("Equation Multiplication", new Quiz(
                new EquationTaskGenerator(
                        1,
                        11,
                        EnumSet.of(MathTask.Operation.MULTIPLICATION)
                ), 5)
        );

        map.put("Equation Division", new Quiz(
                new EquationTaskGenerator(
                        1,
                        11,
                        EnumSet.of(MathTask.Operation.DIVISION)
                ), 5)
        );

        map.put("Equation Mixed Operations", new Quiz(
                new EquationTaskGenerator(
                        0,
                        11,
                        EnumSet.of(MathTask.Operation.ADDITION, MathTask.Operation.SUBTRACTION, MathTask.Operation.MULTIPLICATION, MathTask.Operation.DIVISION)
                ), 10)
        );

        map.put("Min > Max fail", new Quiz(
                new EquationTaskGenerator(
                        11,
                        5,
                        EnumSet.of(MathTask.Operation.ADDITION)
                ), 5)
        );

        map.put("Invalid Operation fail", new Quiz(
                new EquationTaskGenerator(
                        0,
                        10,
                        EnumSet.noneOf(MathTask.Operation.class)
                ), 5)
        );

        map.put("Always Zero fail", new Quiz(
                new ExpressionTaskGenerator(
                        0,
                        0,
                        EnumSet.of(MathTask.Operation.DIVISION)
                ), 5)
        );

        map.put("Empty Pool, fail", new Quiz(
                new PoolTaskGenerator(
                        false
                ), 1)
        );

        // Тесты для GroupTaskGenerator
        map.put("Group Basic Operations", new Quiz(
                new GroupTaskGenerator(
                        new ExpressionTaskGenerator(0, 10, EnumSet.of(MathTask.Operation.ADDITION)),
                        new ExpressionTaskGenerator(0, 10, EnumSet.of(MathTask.Operation.SUBTRACTION))
                ), 5)
        );

        map.put("Group Mixed Operations", new Quiz(
                new GroupTaskGenerator(
                        new ExpressionTaskGenerator(0, 10, EnumSet.of(MathTask.Operation.ADDITION, MathTask.Operation.SUBTRACTION)),
                        new EquationTaskGenerator(1, 10, EnumSet.of(MathTask.Operation.MULTIPLICATION, MathTask.Operation.DIVISION))
                ), 10)
        );

        map.put("Group with Invalid Generator, fail", new Quiz(
                new GroupTaskGenerator(
                        new ExpressionTaskGenerator(0, 10, EnumSet.of(MathTask.Operation.ADDITION)),
                        new EquationTaskGenerator(10, 5, EnumSet.of(MathTask.Operation.ADDITION))
                ), 5)
        );

        return map;
    }


    private static void runQuiz(Quiz quiz, Scanner scanner) throws NotValidGenerator {
        System.out.println("Starting Test...   Answer the questions (accuracy is 0.01). ");

        while (!quiz.isFinished()) {
            System.out.println(quiz.nextTask().getText());
            String answer = scanner.nextLine();
            Result result = quiz.provideAnswer(answer);
            System.out.println(switch (result) {
                case OK -> "\nRight Answer.";
                case WRONG -> "\nWrong Answer.";
                case INCORRECT_INPUT -> "\nIncorrect Input, redo this task.";
            });
        }

        System.out.println("\nTest finished! Your mark is: " + quiz.getMark() * 10 + " / 10.0");
    }

    public static void main(String[] args) throws NotValidGenerator {
        Map<String, Quiz> quizMap = getQuizMap();
        System.out.println("List of Tests:");
        quizMap.keySet().forEach(System.out::println);

        try (Scanner scanner = new Scanner(System.in)) {
            String testName;
            do {
                System.out.println("\nEnter Test Name: ");
                testName = scanner.nextLine();
                if (!quizMap.containsKey(testName)) {
                    System.out.println("\nNo Such Test");
                } else if (testName.endsWith("fail")) {
                    System.out.println("\nThis is a error test, are you sure you want to test it? (y/n)");
                    if (!scanner.nextLine().equals("y")) {
                        runQuiz(quizMap.get(testName), scanner);
                        return;
                    }
                }
            } while (!quizMap.containsKey(testName));

            runQuiz(quizMap.get(testName), scanner);
        }
    }
}