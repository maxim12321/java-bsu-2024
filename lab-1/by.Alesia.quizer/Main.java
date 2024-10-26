import by.Alesia.quizer.Quiz;
import by.Alesia.quizer.Task;
import by.Alesia.quizer.TaskGenerator;
import by.Alesia.quizer.generators.EquationTaskGenerator;
import by.Alesia.quizer.generators.ExpressionTaskGenerator;
import by.Alesia.quizer.generators.GroupTaskGenerator;
import by.Alesia.quizer.generators.PoolTaskGenerator;
import by.Alesia.quizer.tasks.TextTask;
import by.Alesia.quizer.tasks.math.MathTask;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @return тесты в {@link Map}, где
 * ключ     - название теста {@link String}
 * значение - сам тест       {@link Quiz}
 */
static Map<String, Quiz> getQuizMap() {
    Map<String, Quiz> quizMap = new HashMap<>();


    TaskGenerator sumExspressionGenerator = new ExpressionTaskGenerator(0, 100, EnumSet.of(MathTask.Operation.ADD, MathTask.Operation.SUB));
    TaskGenerator mulExspressionGenerator = new ExpressionTaskGenerator(0, 500, EnumSet.of(MathTask.Operation.MUL, MathTask.Operation.DIV));

    TaskGenerator sumEquationGenerator = new EquationTaskGenerator(0, 500, EnumSet.of(MathTask.Operation.ADD, MathTask.Operation.SUB));
    TaskGenerator mulEquationGenerator = new EquationTaskGenerator(0, 100, EnumSet.of(MathTask.Operation.MUL, MathTask.Operation.DIV));

    quizMap.put("SUM Exsp", new Quiz(sumExspressionGenerator, 10));
    quizMap.put("MUL Exsp", new Quiz(mulExspressionGenerator, 10));
    quizMap.put("SUM Equat", new Quiz(sumEquationGenerator, 10));
    quizMap.put("MUL Equat", new Quiz(mulEquationGenerator, 10));


    TextTask textTask_1 = new TextTask("Чему (в градусах) равна сумма углов в треугольнике?", "180");
    TextTask textTask_2 = new TextTask("Какое математическое слово означает относительный размер чего-либо?", "Шкала");
    TextTask textTask_3 = new TextTask("Существуют ли простые четные числа (если да, перечислите их через запятую)?", "2");
    TextTask textTask_4 = new TextTask("Как называется геометрическая фигура, описывающая здание национальной библиотеки?", "Ромбокубооктаэдр");
    TextTask textTask_5 = new TextTask("Верно ли, что биссектрисы треугольника пересекаются в одной точке?", "Да");
    TextTask textTask_6 = new TextTask("Как называется точка пересечения медиан в треугольнике?", "Центр масс");
    TextTask textTask_7 = new TextTask("Какой день является числом Пи?", "14 марта");

    PoolTaskGenerator PoolTest = new PoolTaskGenerator(true, textTask_1, textTask_2, textTask_3, textTask_4, textTask_5,
            textTask_6, textTask_7);
    quizMap.put("Text test", new Quiz(PoolTest, 10));


    GroupTaskGenerator groupGenerator = new GroupTaskGenerator(PoolTest, sumEquationGenerator, sumExspressionGenerator, mulEquationGenerator, mulExspressionGenerator);
    quizMap.put("Group test", new Quiz(groupGenerator, 10));

    GroupTaskGenerator intGenerator = new GroupTaskGenerator(sumEquationGenerator, sumExspressionGenerator, mulEquationGenerator, mulExspressionGenerator);

    quizMap.put("Integer test", new Quiz(intGenerator, 10));

    return quizMap;
}

public static void main(String[] args) {
    var quizMap = getQuizMap();
    System.out.println("Введите название теста");
    Scanner input = new Scanner(System.in);
    String name;
    while (true) {
        name = input.nextLine();
        if (quizMap.containsKey(name)) {
            break;
        }
        System.out.println("Теста с данным названием не существует. Введите название теста из списка выше...");
    }
    Quiz quiz = quizMap.get(name);
    Task task;
    if (!quiz.isFinished()) {
        task = quiz.nextTask();
    }
    while (!quiz.isFinished()) {
        task = quiz.nextTask();
        System.out.println(task.getText());
        final String answer = input.nextLine();
        System.out.println(switch (quiz.provideAnswer(answer)) {
            case OK -> "Верный ответ";
            case WRONG -> "Неверный ответ";
            case INCORRECT_INPUT -> "Неккоректный ответ. Попробуйте еще раз";
        });
    }
    System.out.printf("Ваша оценка: %.2f\n", quiz.getMark());

}