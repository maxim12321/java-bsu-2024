package by.MikhailNaumovich.quizer;

import by.MikhailNaumovich.quizer.generators.math.EquationTaskGenerator;
import by.MikhailNaumovich.quizer.generators.math.ExpressionTaskGenerator;

import by.MikhailNaumovich.quizer.generators.GroupTaskGenerator;
import by.MikhailNaumovich.quizer.generators.PoolTaskGenerator;

import by.MikhailNaumovich.quizer.generators.sequence.SequenceTaskGenerator;

import by.MikhailNaumovich.quizer.tasks.TextTask;
import by.MikhailNaumovich.quizer.tasks.math.MathEnum;
import by.MikhailNaumovich.quizer.tasks.sequence.PatternTypeEnum;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Main {
        
    public static Map<String, Quiz> getQuizMap() {
        HashMap<String, Quiz> map = new HashMap<>();
        map.put("Equations with basic operations", new Quiz(
                new EquationTaskGenerator(
                        0,10,
                        EnumSet.of(MathEnum.ADD,
                                MathEnum.SUBTRACT)
                ), 10
        ));

        map.put("Expressions with basic operations", new Quiz(
                new ExpressionTaskGenerator(
                        0,20,
                        EnumSet.of(MathEnum.ADD,
                                MathEnum.SUBTRACT,
                                MathEnum.MULTIPLY)
                ), 10
        ));

        map.put("Duplicate pool for History", new Quiz(
                new PoolTaskGenerator<>(
                        true,
                        new TextTask("In what year was the Declaration of Independce signed?", "1776"),
                        new TextTask("Which event marked the beginning of the Middle Ages?", "The fall of Rome")
                ), 4
        ));

        map.put("Non-sufficent pool without duplicates for Philosophy", new Quiz(
                new PoolTaskGenerator<>(
                        false,
                        new TextTask("Who is the author of Enten - Eller? (surname)", "Kierkegaard"),
                        new TextTask("Which philosophical branch examines the fundamental nature of reality?", "Metaphysics"),
                        new TextTask("Take a guess, will this test ever be finished?", "No")

                ), 4
        ));

        map.put("Sufficent pool without duplicates for Philosophy", new Quiz(
                new PoolTaskGenerator<>(
                        false,
                        new TextTask("Who is the author of Enten - Eller? (surname)", "Kierkegaard"),
                        new TextTask("Which philosophical branch examines the fundamental nature of reality?", "Metaphysics"),
                        new TextTask("Take a guess, will this test ever be finished?", "Yes")

                ), 3
        ));

        map.put("Chem Group with non-sufficent pools", new Quiz(
                new GroupTaskGenerator<>(
                        new PoolTaskGenerator<>(
                                false,
                                new TextTask("Write the molecular formula for propane?", "C3H8"),
                                new TextTask("Write the molecular formula for carbon monoxide?", "CO")),
                        new PoolTaskGenerator<>(
                                false,
                                new TextTask("What is the rounded molar mass of Lead?", "207"),
                                new TextTask("What is the rounded molar mass of Helium?", "4"))
                ), 5
        ));
        
        map.put("Chem Group with insufficent pools", new Quiz(
                new GroupTaskGenerator<>(
                        new PoolTaskGenerator<>(
                                false,
                                new TextTask("Write the molecular formula for propane?", "C3H8"),
                                new TextTask("Write the molecular formula for carbon monoxide?", "CO")),
                        new PoolTaskGenerator<>(
                                false,
                                new TextTask("What is the rounded molar mass of Lead?", "207"),
                                new TextTask("What is the rounded molar mass of Helium?", "4"))
                ), 5
        ));

        map.put("Chem Group with sufficent pools", new Quiz(
                new GroupTaskGenerator<>(
                        new PoolTaskGenerator<>(
                                false,
                                new TextTask("Write the molecular formula for propane?", "C3H8"),
                                new TextTask("Write the molecular formula for carbon monoxide?", "CO")),
                        new PoolTaskGenerator<>(
                                false,
                                new TextTask("What is the rounded molar mass of Lead?", "207"),
                                new TextTask("What is the rounded molar mass of Helium?", "4"))
                ), 4
        ));

        map.put("ChemMath Group with sufficent pools", new Quiz(
                new GroupTaskGenerator<>(
                        new PoolTaskGenerator<>(
                                false,
                                new TextTask("Write the molecular formula for propane?", "C3H8"),
                                new TextTask("Write the molecular formula for carbon monoxide?", "CO")),
                        new ExpressionTaskGenerator(
                                1,
                                11,
                                EnumSet.of(MathEnum.MULTIPLY)
                )), 4
        ));

        map.put("Valid expressions. Edge cases with div", new Quiz(
                new ExpressionTaskGenerator(
                        0,1,
                        EnumSet.of(MathEnum.DIVIDE)
                ), 10
        ));

        map.put("Valid equations. Edge cases with div and mult", new Quiz(
                new EquationTaskGenerator(
                        0,1,
                        EnumSet.of(MathEnum.MULTIPLY,
                                MathEnum.DIVIDE)
                ), 10
        ));

        map.put("Division with precision", new Quiz(
                new ExpressionTaskGenerator(
                        1,7,
                        EnumSet.of(MathEnum.DIVIDE)
                ), 10
        ));

        map.put("Fibonacci and Square Sequences", new Quiz(
                new SequenceTaskGenerator(
                        15,
                        EnumSet.of(PatternTypeEnum.FIBONACCI, PatternTypeEnum.SQUARE),
                        4,
                        4
                ),
                5
            ));

        map.put("All Patterns Sequences", new Quiz(
                new SequenceTaskGenerator(
                        10,  
                        EnumSet.allOf(PatternTypeEnum.class), 
                        3,   
                        5
            ),
            10
        ));
        
        return map;

    }
    public static void main(String[] args) {
        Map<String, Quiz> quizMap = getQuizMap();
        System.out.println("Available tests:");
        quizMap.keySet().forEach(System.out::println);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Input the name of the test you prefer...");
        String testName = scanner.nextLine();
        while (!quizMap.containsKey(testName)) {
            System.out.println("Incorect input. Choose one of the available test, please!");
            testName = scanner.nextLine();
        }
        Quiz quiz = quizMap.get(testName);

        System.out.println("Beginning test. Prepare to answer " + quiz.getTasksAmount() + " questions.");
        while (!quiz.isFinished()) {
            System.out.println(quiz.nextTask().getText());
            String answer = scanner.nextLine();
            Result result = quiz.provideAnswer(answer);
            System.out.println(switch (result) {
                case OK -> "Your answer is correct!";
                case WRONG -> "Your answer is incorrect!";
                case INCORRECT_INPUT -> "Incorrect input. Please, try again!";
            });
        }
        scanner.close();
        System.out.println("You did well! Your grade: " + quiz.getMark());
    }
}
