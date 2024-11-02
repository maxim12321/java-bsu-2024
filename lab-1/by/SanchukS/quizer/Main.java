package by.SanchukS.quizer;

import by.SanchukS.quizer.exceptions.NullArgumentException;
import by.SanchukS.quizer.generators.*;
import by.SanchukS.quizer.tasks.TextTask;

import java.util.*;

public class Main {
public static void main(String[] args) {
        Map<String, Quiz> quizMap = getQuizMap();
        Quiz quiz = quizMap.get(getQuizName());
        while (quiz == null) {
            System.out.println("Please try again");
            quiz = quizMap.get(getQuizName());
        }

        try {
            System.out.println("Ready?");
            Thread.sleep(1000);
            System.out.println("Steady!");
            Thread.sleep(1000);
            System.out.println("Go!");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        runQuiz(quiz);
        double mark = quiz.getMark();

        System.out.println("Mark: " + mark);
    }

    /**
     * @return тесты в {@link Map}, где
     * ключ     - название теста {@link String}
     * значение - сам тест       {@link Quiz}
     */
    static Map<String, Quiz> getQuizMap() {
        ExpressionTaskGenerator simpleExpressionsGenerator = new ExpressionTaskGenerator(
                1, 100, EnumSet.of(Operation.SUM, Operation.SUBTRACT, Operation.DIVIDE)
        );
        ExpressionTaskGenerator advancedExpressionsGenerator = new ExpressionTaskGenerator(
                -100, 100, EnumSet.of(Operation.MULTIPLY)
        );
        EquationTaskGenerator simpleEquationsGenerator = new EquationTaskGenerator(
                1, 100, EnumSet.of(Operation.SUM, Operation.SUBTRACT, Operation.DIVIDE)
        );
        EquationTaskGenerator advancedEquationsGenerator = new EquationTaskGenerator(
                -100, 100, EnumSet.of(Operation.MULTIPLY)
        );
        GroupTaskGenerator groupTaskGenerator = new GroupTaskGenerator(
                simpleExpressionsGenerator, advancedExpressionsGenerator, simpleEquationsGenerator
        );

        List<Task> tasks = new ArrayList<>();
        {
            tasks.add(new TextTask(
                    """
                            По небу летели верблюды: один синий, другой на север.
                            Вопрос: сколько стоит килограмм асфальта, если ёжику 24 года?""",
                    "Пятнадцать"));
            tasks.add(new TextTask(
                    """
                            Из пункта А в направление пункта Б выехал велосипедист со скоростью 15км/ч.
                            Из пункта Б выехал грузовик со скоростью в 3 раза большей скорости велосипедиста.
                            А через час за грузовиком выехал автомобиль со скоростью 60км/ч.n\
                            Вопрос: сколько всего транспортных средств было упомянуто в задаче?""",
                    "3"));
        }

        PoolTaskGenerator poolTaskGenerator = new PoolTaskGenerator(true, tasks);
        VelocityTaskGenerator velocityTaskGenerator = new VelocityTaskGenerator(10, 200);


        GroupTaskGenerator textTaskGenerator = new GroupTaskGenerator(poolTaskGenerator, velocityTaskGenerator);

        for(int i = 0; i < 10; ++i)
            tasks.add(groupTaskGenerator.generate());

        PoolTaskGenerator randomPoolTaskGenerator = new PoolTaskGenerator(false, tasks);
        groupTaskGenerator.addTaskGenerator(randomPoolTaskGenerator);

        // Quizes
        Quiz simpleMathQuiz = new Quiz(
                new GroupTaskGenerator(simpleExpressionsGenerator, simpleEquationsGenerator),
                10
        );

        Quiz advancedEquationsQuiz = new Quiz(
                advancedEquationsGenerator,
                10
        );

        Quiz textQuiz = new Quiz(textTaskGenerator, 3);

        Quiz randomTaskQuiz = new Quiz(randomPoolTaskGenerator, 10);

        Quiz allTasksQuiz = new Quiz(groupTaskGenerator, 10);

        return Map.of(
                "Simple Math Quiz", simpleMathQuiz,
                "Advanced Math Quiz", advancedEquationsQuiz,
                "Text Quiz", textQuiz,
                "Random Task Quiz", randomTaskQuiz,
                "All Tasks", allTasksQuiz
        );
    }

    static String getQuizName() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter quiz name: ");
        return scanner.nextLine();
    }

    static void runQuiz(Quiz quiz) {
        if (quiz == null) throw new NullArgumentException("quiz");

        Scanner scanner = new Scanner(System.in);
        while (!quiz.isFinished()) {
            Task currentTask = quiz.nextTask();
            System.out.println(currentTask.getText());
            String answer = scanner.nextLine();
            System.out.println(quiz.provideAnswer(answer).name() + "\n");
        }
    }
}
