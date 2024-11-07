package by.volkovets.quizer;

import java.util.TreeMap;
import java.util.Map;
import java.util.Scanner;
import java.util.EnumSet;

import by.volkovets.quizer.generators.*;
import by.volkovets.quizer.generators.math.*;
import by.volkovets.quizer.tasks.TextTask;
import by.volkovets.quizer.tasks.WhoLooseTask;
import by.volkovets.quizer.tasks.math.MathTask;
import by.volkovets.quizer.tasks.math.EquationTask;
import by.volkovets.quizer.tasks.math.ExpressionTask;

public class Main {
        static Map<String, Quiz> getQuizMap() {
                Map<String, Quiz> quiz = new TreeMap<String, Quiz>();
                quiz.put("Expressions exception only", new Quiz(
                                new ExpressionTaskGenerator(
                                                23,
                                                10, EnumSet.of(MathTask.Operation.Difference)),
                                3));
                quiz.put("Expressions Only", new Quiz(
                                new ExpressionTaskGenerator(
                                                0,
                                                10,
                                                EnumSet.of(MathTask.Operation.Difference,
                                                                MathTask.Operation.Multiplication)),
                                3));
                quiz.put("Equations only", new Quiz(
                                new EquationTaskGenerator(
                                                10,
                                                50, EnumSet.of(MathTask.Operation.Multiplication)),
                                5));
                quiz.put("Group of Equations and Expressions", new Quiz(
                                new GroupTaskGenerator(
                                                new EquationTaskGenerator(
                                                                0,
                                                                10,
                                                                EnumSet.of(MathTask.Operation.Difference,
                                                                                MathTask.Operation.Sum,
                                                                                MathTask.Operation.Difference)),
                                                new ExpressionTaskGenerator(
                                                                10,
                                                                50,
                                                                EnumSet.of(MathTask.Operation.Difference,
                                                                                MathTask.Operation.Multiplication,
                                                                                MathTask.Operation.Division)),
                                                new ExpressionTaskGenerator(
                                                                0,
                                                                10,
                                                                EnumSet.of(MathTask.Operation.Difference,
                                                                                MathTask.Operation.Multiplication))),
                                3));
                quiz.put("TextTask", new Quiz(
                                new TextTaskGenerator(23, 45),
                                1));
                quiz.put("Group of Everything", new Quiz(
                                new GroupTaskGenerator(
                                                new EquationTaskGenerator(
                                                                0,
                                                                10,
                                                                EnumSet.of(MathTask.Operation.Division,
                                                                                MathTask.Operation.Multiplication,
                                                                                MathTask.Operation.Sum)),
                                                new ExpressionTaskGenerator(
                                                                0,
                                                                50,
                                                                EnumSet.of(MathTask.Operation.Difference,
                                                                                MathTask.Operation.Multiplication,
                                                                                MathTask.Operation.Division)),
                                                new WhoLooseTaskGenerator(
                                                                "Дима",
                                                                "Лёха"),
                                                new TextTaskGenerator(5, 12)),
                                5));
                quiz.put("Pool with duplicates", new Quiz(
                                new PoolTaskGenerator(
                                                true,
                                                new WhoLooseTask("Миша", "Леша"),
                                                new TextTask(1, 12, 13),
                                                new EquationTask(1, MathTask.Operation.Difference, 23, 56),
                                                new ExpressionTask(MathTask.Operation.Multiplication, 12, 23)

                                ), 4));

                quiz.put("Pool without duplicates", new Quiz(
                                new PoolTaskGenerator(
                                                false,
                                                new WhoLooseTask("Миша", "Леша"),
                                                new TextTask(0, 12, 13),
                                                new EquationTaskGenerator(2, 23,
                                                                EnumSet.of(MathTask.Operation.Difference,
                                                                                MathTask.Operation.Sum))
                                                                .generate(),
                                                new ExpressionTask(MathTask.Operation.Difference, 23, 56)

                                ), 3));
                quiz.put("Pool without duplicates and taskCount >> ", new Quiz(
                                new PoolTaskGenerator(
                                                false,
                                                new WhoLooseTask("Миша", "Леша"),
                                                new TextTask(0, 15, 13),
                                                new EquationTaskGenerator(2, 23,
                                                                EnumSet.of(MathTask.Operation.Division,
                                                                                MathTask.Operation.Multiplication))
                                                                .generate(),
                                                new ExpressionTask(MathTask.Operation.Difference, 23, 56)

                                ), 5));
                quiz.put("Group with exceptions", new Quiz(
                                new GroupTaskGenerator(
                                                new EquationTaskGenerator(
                                                                2,
                                                                10,
                                                                EnumSet.of(MathTask.Operation.Division,
                                                                                MathTask.Operation.Multiplication)),
                                                new ExpressionTaskGenerator(
                                                                78,
                                                                50,
                                                                EnumSet.of(MathTask.Operation.Difference,
                                                                                MathTask.Operation.Division)),
                                                new TextTaskGenerator(2, 23)),
                                5));

                quiz.put("Group with exceptions only", new Quiz(
                                new GroupTaskGenerator(
                                                new EquationTaskGenerator(
                                                                12,
                                                                10,
                                                                EnumSet.of(MathTask.Operation.Difference,
                                                                                MathTask.Operation.Division)),
                                                new ExpressionTaskGenerator(
                                                                78,
                                                                50,
                                                                EnumSet.of(MathTask.Operation.Sum,
                                                                                MathTask.Operation.Multiplication))),
                                1));

                return quiz;
        }

        public static void main(String[] args) {
                Map<String, Quiz> quizMap = getQuizMap();

                System.out.println("Список тестов:");
                for (var elem : quizMap.entrySet()) {
                        System.out.println(elem.getKey());
                }
                String testName;
                Scanner scanner = new Scanner(System.in);
                do {
                        System.out.println("Введите название теста...");
                        testName = scanner.nextLine();
                } while (!quizMap.containsKey(testName));
                Quiz quiz = quizMap.get(testName);

                // System.out.println(quiz.getMark()); // вызовет исключение

                while (!quiz.isFinished()) {
                        System.out.println(quiz.nextTask().getText());
                        String answer = scanner.nextLine();
                        Result result = quiz.provideAnswer(answer);
                        System.out.println(switch (result) {
                                case OK -> "Ответ верный!";
                                case WRONG -> "Ответ неверный!";
                                case INCORRECT_INPUT ->
                                        "Ввод некорректный. Попробуйте ещё раз!";
                        });
                }

                System.out.println("Тест окончен! Ваша оценка: " + quiz.getMark());
                scanner.close();
                System.out.println("Кол - во некоректных вводов: " + quiz.getIncorrectInputNumber());
                System.out.println("Кол - во правильных ответов: " + quiz.getCorrectAnswerNumber());
                System.out.println("Кол - во неправильных ответов: " + quiz.getWrongAnswerNumber());

        }
}