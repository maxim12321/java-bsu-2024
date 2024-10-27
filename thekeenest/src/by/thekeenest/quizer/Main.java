package by.thekeenest.quizer;

import by.thekeenest.quizer.generators.GroupTaskGenerator;
import by.thekeenest.quizer.generators.PoolTaskGenerator;
import by.thekeenest.quizer.generators.TaskGenerator;
import by.thekeenest.quizer.generators.TextTaskGenerator;
import by.thekeenest.quizer.generators.math.EquationTaskGenerator;
import by.thekeenest.quizer.generators.math.ExpressionTaskGenerator;
import by.thekeenest.quizer.generators.math.MathTaskGenerator;
import by.thekeenest.quizer.tasks.Task;
import by.thekeenest.quizer.tasks.TextTask;
import by.thekeenest.quizer.tasks.math.AbstractMathTask;
import by.thekeenest.quizer.tasks.math.EquationMathTask;
import by.thekeenest.quizer.tasks.math.ExpressionMathTask;
import by.thekeenest.quizer.tasks.math.MathTask;

import java.text.DecimalFormat;
import java.util.*;

public class Main {
    public static Map<String, Quiz> getQuizMap() {
        Map<String, Quiz> quizMap = new HashMap<>();

        // Математический тест
        EnumSet<MathTask.Operation> ops = EnumSet.of(MathTask.Operation.PLUS, MathTask.Operation.MINUS, MathTask.Operation.PRODUCT);
        ExpressionTaskGenerator expressionGenerator = new ExpressionTaskGenerator(2, 9, ops);
        EquationTaskGenerator equationGenerator = new EquationTaskGenerator(1, 15, ops);

        // Пул задач с дублирующимися задачами
        PoolTaskGenerator<Task> poolGenerator = new PoolTaskGenerator<>(true,
                new EquationMathTask(3, 6, MathTask.Operation.PLUS, true),
                new ExpressionMathTask(7, 2, MathTask.Operation.MINUS),
                new ExpressionMathTask(4, 8, MathTask.Operation.PRODUCT)
        );

        quizMap.put("pool", new Quiz(poolGenerator, 3));

        // Групповой генератор задач
        GroupTaskGenerator<Task> groupGenerator = new GroupTaskGenerator(expressionGenerator, equationGenerator);
        quizMap.put("group", new Quiz(groupGenerator, 2));

        // Текстовый тест
        TaskGenerator<Task> textGenerator = new TextTaskGenerator("Столица России?", "Москва");
        quizMap.put("geography", new Quiz(textGenerator, 1));


        TaskGenerator<Task> poolBsu = new PoolTaskGenerator<>(true,
                new TextTask("Лучший препод по джаве? ", "Макс"),
                new TextTask("Пароль, пять букв: ", "Гойда"),
                new TextTask("Сос ... - вставьте пропущенное слово. С маленькой буквы. ", "репос"),
                new TextTask("Свадьба - это\n1. Круто\n2. Стихийное бедствие\n3. Дорого\n\nВведите номер ответа: ", "2"));


        quizMap.put("bsu", new Quiz(poolBsu, 4));


        return quizMap;
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Quiz> quizMap = getQuizMap();

        while (true) {
            System.out.println("Введите название теста (доступны: " +
                    String.join(", ", quizMap.keySet()) + "):");
            String quizName = scanner.nextLine();

            Quiz quiz = quizMap.get(quizName);
            if (quiz == null) {
                System.out.println("Тест не найден. Попробуйте еще раз.");
                continue;
            }

            while (!quiz.isFinished()) {
                Task task = quiz.getCurrentTask();
                System.out.println(task.getText());
                String answer = scanner.nextLine();

                Result result = quiz.provideAnswer(answer);
                switch (result) {
                    case OK:
                        System.out.println("Правильно!");
                        if (!quiz.isFinished())
                            quiz.nextTask();
                        break;
                    case WRONG:
                        System.out.println("Неправильно!");
                        if (!quiz.isFinished())
                            quiz.nextTask();
                        break;
                    case INCORRECT_INPUT:
                        System.out.println("Некорректный ввод. Пройдите тест заново.");
                        continue;
                }
            }
            DecimalFormat df = new DecimalFormat();
            df.setMaximumFractionDigits(2);
            System.out.println(String.format("Тест завершен. Ваша оценка: %s %%", df.format(quiz.getMark() * 100)));
            System.out.println("Хотите пройти другой тест? (да/нет)");
            if (!scanner.nextLine().equalsIgnoreCase("да")) {
                break;
            }
        }
        scanner.close();
    }
}