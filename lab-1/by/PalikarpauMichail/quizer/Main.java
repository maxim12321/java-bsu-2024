package by.PalikarpauMichail.quizer;

import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;
import java.util.EnumSet;

import by.PalikarpauMichail.quizer.generators.*;
import by.PalikarpauMichail.quizer.tasks.*;
import by.PalikarpauMichail.quizer.tasks.math.MathTask;
public class Main {
    /**
     * @return тесты в {@link Map}, где
     * ключ     - название теста {@link String}
     * значение - сам тест       {@link Quiz}
     */
    static Map<String, Quiz> getQuizMap() {
        Map<String, Quiz> quizMap = new HashMap<String, Quiz>();

        EnumSet<MathTask.Operation> allOperations = EnumSet.allOf(MathTask.Operation.class);

        ExpressionTaskGenerator easyMathTaskGenerator = new ExpressionTaskGenerator(1, 20, 
            EnumSet.of(MathTask.Operation.ADDITION, MathTask.Operation.SUBTRACTION));
        ExpressionTaskGenerator expressionTaskGenerator = new ExpressionTaskGenerator(-100, 100, allOperations);
        EquationTaskGenerator equationTaskGenerator = new EquationTaskGenerator(-100, 100, allOperations);
        TextMathTaskGenerator textMathTaskGenerator = new TextMathTaskGenerator(0, 100, allOperations);


        GroupTaskGenerator<Task> groupTaskGenerator = new GroupTaskGenerator<>(expressionTaskGenerator, equationTaskGenerator);

        PoolTaskGenerator<TextTask> poolTaskGenerator = new PoolTaskGenerator<>(
            false, 
            new TextTask("Назовите столицу Португалии", "Лиссабон"),
            new TextTask("Назовите столицу Чили", "Сантьяго"),
            new TextTask("Назовите самое большое млекопитающее", "Синий кит"),
            new TextTask("Назовите самый большой океан в мире", "Тихий океан")
        );

        PoolTaskGenerator<Task> exceptionGenerator = new PoolTaskGenerator<>(false, new Task[]{});
    
        Quiz easyMathTasksQuiz = new Quiz(easyMathTaskGenerator, 10);
        Quiz textQuestionsQuiz = new Quiz(poolTaskGenerator, 4);       
        Quiz allArithmeticQuiz = new Quiz(groupTaskGenerator, 5);
        Quiz textMathQuiz = new Quiz(textMathTaskGenerator, 3);
        Quiz exceptionQuiz = new Quiz(exceptionGenerator, 1);

        quizMap.put("Простые примеры", easyMathTasksQuiz);
        quizMap.put("Текстовые вопросы", textQuestionsQuiz);
        quizMap.put("Арифметические примеры и уравнения", allArithmeticQuiz);
        quizMap.put("Текстовые задачи", textMathQuiz);
        quizMap.put("Неработающий", exceptionQuiz); // бросит исключение, т.к. в нём нет заданий
        return quizMap;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in,  "UTF-8");

        var quizMap = getQuizMap();
        String testName;
        do {
            System.out.println("Введите название теста...");
            testName = scanner.nextLine();
        } while (!quizMap.containsKey(testName));

        System.out.println("Название теста введёно корректно");
        Quiz quiz = quizMap.get(testName);

        while (!quiz.isFinished()) {
            System.out.println(quiz.nextTask().getText());
            String answer = scanner.nextLine();
            quiz.provideAnswer(answer);
        }

        System.out.println("Ваша оценка: " + quiz.getMark());


        scanner.close();
    }
}
