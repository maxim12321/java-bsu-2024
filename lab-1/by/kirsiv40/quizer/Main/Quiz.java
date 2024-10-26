package by.kirsiv40.quizer.Main;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

import by.kirsiv40.quizer.Generators.GroupTaskGenerator;
import by.kirsiv40.quizer.Generators.PoolTaskGenerator;
import by.kirsiv40.quizer.Generators.Math.EquationMathTaskGenerator;
import by.kirsiv40.quizer.Generators.Math.ExpressionMathTaskGenerator;
import by.kirsiv40.quizer.Main.Exceptions.QuizIsNotFinished;
import by.kirsiv40.quizer.Generators.TaskGenerator;
import by.kirsiv40.quizer.Tasks.Task;
import by.kirsiv40.quizer.Tasks.TextTask;
import by.kirsiv40.quizer.Tasks.Math.EquationMathTask;
import by.kirsiv40.quizer.Tasks.Math.ExpressionMathTask;
import by.kirsiv40.quizer.Tasks.Math.MathTask;
import by.kirsiv40.quizer.Tasks.Math.MathTask.Operation;

/**
 * Class, который описывает один тест
 */
public class Quiz {
    /**
     * @param generator генератор заданий
     * @param taskCount количество заданий в тесте
     */
    private TaskGenerator<? extends Task> gen;
    private int taskCount = 0;
    private Task curTask;
    private Result res;

    private int correct = 0;
    private int wrong = 0;
    private int inc_inp = 0;

    Quiz(TaskGenerator<? extends Task> generator, int tC) {
        gen = generator;
        taskCount = tC;
    }

    /**
     * @return задание, повторный вызов вернет слелующее
     * @see Task
     */
    Task nextTask() {
        if (res == Result.INCORRECT_INPUT) {
            return curTask;
        }
        curTask = gen.generate();
        return curTask;
    }

    /**
     * Предоставить ответ ученика. Если результат {@link Result#INCORRECT_INPUT}, то
     * счетчик неправильных
     * ответов не увеличивается, а {@link #nextTask()} в следующий раз вернет тот же
     * самый объект {@link Task}.
     */
    Result provideAnswer(String answer) {
        res = curTask.validate(answer);
        switch (res) {
            case OK -> correct++;
            case WRONG -> wrong++;
            case INCORRECT_INPUT -> taskCount++;
        }
        taskCount--;
        System.out.println(switch(res) {
            case OK -> "Ваш ответ верен";
            case WRONG -> "Вы обманываете";
            case INCORRECT_INPUT -> "Ввод некорректен. Повторите попытку";
        });
        return res;
    }

    /**
     * @return завершен ли тест
     */
    boolean isFinished() {
        return taskCount <= 0;
    }

    /**
     * @return количество правильных ответов
     */
    int getCorrectAnswerNumber() {
        return correct;
    }

    /**
     * @return количество неправильных ответов
     */
    int getWrongAnswerNumber() {
        return wrong;
    }

    /**
     * @return количество раз, когда был предоставлен неправильный ввод
     */
    int getIncorrectInputNumber() {
        return inc_inp;
    }

    /**
     * @return оценка, которая является отношением количества правильных ответов к
     *         количеству всех вопросов.
     *         Оценка выставляется только в конце!
     */
    double getMark() {
        if (this.isFinished()) {
            return (double) (this.getCorrectAnswerNumber())
                    / (this.getCorrectAnswerNumber() + this.getWrongAnswerNumber());
        }
        throw new QuizIsNotFinished("Test is not finished");
    }

    /**
     * @return тесты в {@link Map}, где
     *         ключ - название теста {@link String}
     *         значение - сам тест {@link Quiz}
     */
    static Map<String, Quiz> getQuizMap() {
        var result = new HashMap<String, Quiz>();
        result.put("name", new Quiz(new ExpressionMathTaskGenerator(1, 5, EnumSet.allOf(Operation.class)), 5));
        
        result.put("Equation test",
                new Quiz(new EquationMathTaskGenerator(-100, 100, EnumSet.allOf(Operation.class)), 5));
        
        result.put("Pool test", new Quiz(new PoolTaskGenerator<MathTask>(true, new EquationMathTask("15 + 15", 30),
                new ExpressionMathTask("52 + 52", 104)), 5));

        result.put("Text test",
                new Quiz(new PoolTaskGenerator<Task>(false, new TextTask("Как зовут Вадима?", "Вадик"),
                        new TextTask("Как зовут Дмитрия?", "Дмитрий"), new TextTask("Как зовут Кирилла?", "Кирилл")),
                        3));

        result.put("MathExpressionPM",
                new Quiz(new ExpressionMathTaskGenerator(-20, 21, EnumSet.of(Operation.PLUS, Operation.MINUS)), 5));

        result.put("Check no zero division", new Quiz(new EquationMathTaskGenerator(0, 2, EnumSet.of(Operation.DIV)), 5));

        result.put("Check no zero division expression", new Quiz(new EquationMathTaskGenerator(0, 1, EnumSet.of(Operation.DIV)), 5));

        result.put("MathExpression", new Quiz(new ExpressionMathTaskGenerator(-20, 21,
                EnumSet.of(Operation.PLUS, Operation.MINUS, Operation.DIV, Operation.PROD)), 5));

        result.put("GroupGen test",
                new Quiz(new GroupTaskGenerator<Task>(
                        new ExpressionMathTaskGenerator(-20, 21, EnumSet.of(Operation.PLUS)),
                        new ExpressionMathTaskGenerator(-4, 5, EnumSet.of(Operation.MINUS)), new PoolTaskGenerator<>(
                                false, new TextTask("мяу или мур?", "мяу"), new TextTask("мяу или гав?", "мяу"))),
                        10));

        return result;
    }
}