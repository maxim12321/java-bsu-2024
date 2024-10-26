package by.chewy_pegasus.quizer;

import by.chewy_pegasus.quizer.exceptions.QuizNotFinishedException;
import by.chewy_pegasus.quizer.generators.EquationTaskGenerator;
import by.chewy_pegasus.quizer.generators.ExpressionTaskGenerator;
import by.chewy_pegasus.quizer.generators.GroupTaskGenerator;
import by.chewy_pegasus.quizer.generators.PoolTaskGenerator;
import by.chewy_pegasus.quizer.tasks.EquationTask;
import by.chewy_pegasus.quizer.tasks.ExpressionTask;
import by.chewy_pegasus.quizer.tasks.TextTaskWithAnswers;
import by.chewy_pegasus.quizer.tasks.TextTaskWithoutAnswers;
import by.chewy_pegasus.quizer.tasks.math.MathTask;

import java.util.*;

public class Main {
    public static void main(String[] args) throws QuizNotFinishedException {
        Map<String, Quiz> able = getQuizMap();
        System.out.println("Доступные тесты: ");
        for (String name : able.keySet()) {
            System.out.println(name);
        }
        System.out.println("Введите название теста...");
        Scanner in = new Scanner(System.in);
        String testName = in.nextLine();
        while (!able.containsKey(testName)) {
            System.out.println("Тест не найден. Попробуйте ещё раз: ");
            testName = in.nextLine();
        }
        Quiz quiz = able.get(testName);
        while (!quiz.isFinished()) {
            Task task = quiz.nextTask();
            String taskText = task.getText();
            System.out.println(taskText);
            String answer = in.nextLine();
            Result result = quiz.provideAnswer(answer);
            printResult(result);
        }
        double mark = quiz.getMark();
        System.out.println("Your mark for test is: " + mark);
    }

    private static void printResult(Result result) {
        if (result == Result.OK) {
            System.out.println("OK");
        } else if (result == Result.WRONG) {
            System.out.println("WRONG");
        } else {
            System.out.println("Incorrect Input Format");
        }
    }

    /**
     * @return тесты в {@link Map}, где
     * ключ     - название теста {@link String}
     * значение - сам тест       {@link Quiz}
     */
    static Map<String, Quiz> getQuizMap() {
        final EnumSet<MathTask.Operation> full = EnumSet.of(MathTask.Operation.SUM, MathTask.Operation.DIFF, MathTask.Operation.MULT, MathTask.Operation.DIV);
        return Map.ofEntries(
                Map.entry("MathExpr", new Quiz(
                        new ExpressionTaskGenerator(1, 10, full),
                        10
                )),
                Map.entry("MathEq", new Quiz(
                        new EquationTaskGenerator(1, 10, full),
                        10
                )),
                Map.entry("MathPool", new Quiz(
                        new PoolTaskGenerator(true, new ExpressionTask(14,88,'+'),
                                                                new EquationTask(true, 3,333,'*')),
                        3)
                ),
                Map.entry("MathGroupHard", new Quiz(
                        new GroupTaskGenerator(new ExpressionTaskGenerator(-100, 100, full),
                                                            new EquationTaskGenerator(-100, 100, full)),
                        5)
                ),
                Map.entry("TextTaskNoAns", new Quiz(
                        new PoolTaskGenerator(false, new TextTaskWithoutAnswers("Кто проживает на дне океана?","Губка Боб"),
                                                                new TextTaskWithoutAnswers("Какую отметку нужно поставить мне за лабу?", "10"),
                                                                new TextTaskWithoutAnswers("Сколько денег Вы бы заплатили за этот проект?", "1kk$")),
                        3)
                ),
                Map.entry("TextTaskWithAns", new Quiz(
                        new PoolTaskGenerator(false,
                                new TextTaskWithAnswers("Сколько пальцев у человека на одной руке?","5", new ArrayList<>(Arrays.asList("1", "2", "3", "4", "5"))),
                                new TextTaskWithAnswers("Кто исполнитель трека \"Niggers in Paris\"?","Kanye West", new ArrayList<>(Arrays.asList("Александр Солодуха", "SHAMAN", "Kanye West", "Казанский казачий хор", "Seryoga"))),
                                new TextTaskWithAnswers("Вставьте пропущенное слово: Сос _","Репос", new ArrayList<>(Arrays.asList("Отсос", "Айкос", "Навоз", "Репос", "Рябый Вячеслав Васильевич"))),
                                new TextTaskWithAnswers("БГУ это _","Бренд", new ArrayList<>(Arrays.asList("Круто", "Бренд", "университет", "Белорусский государственный университет", "Сос репос"))),
                                new TextTaskWithAnswers("Как расшифровывается МСС?","_", new ArrayList<>(Arrays.asList("Многопроцессорные системы и сети", "Модернизация систем управления", "Международный центр статистики", "Мы соревнуемся в съедании", "Модульная система строительства"))),
                                new TextTaskWithAnswers("Какая группа умнее остальных?","1 группа", new ArrayList<>(Arrays.asList("1 группа", "2 группа", "3 группа", "4 группа", "КБ"))),
                                new TextTaskWithAnswers("Лучшая категория фильмов","Gachimuchi", new ArrayList<>(Arrays.asList("Стримы папича", "Научные", "Gachimuchi", "Мелодрама", "Там пыщ пыщ трах бабах рембо первая кровь убить всех"))),
                                new TextTaskWithAnswers("Мой любимый смешарик","Крош", new ArrayList<>(Arrays.asList("Крош", "Нюша", "Кар-карыч", "Копатыч", "Ёжик")))),
                        8)
                )
        );
    }
}
