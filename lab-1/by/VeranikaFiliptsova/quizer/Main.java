package by.VeranikaFiliptsova.quizer;

import by.VeranikaFiliptsova.quizer.generators.GroupTaskGenerator;
import by.VeranikaFiliptsova.quizer.generators.PoolTaskGenerator;
import by.VeranikaFiliptsova.quizer.generators.math.EquationMathTaskGenerator;
import by.VeranikaFiliptsova.quizer.generators.math.ExpressionMathTaskGenerator;
import by.VeranikaFiliptsova.quizer.generators.math.TextExpressionMathTaskGenerator;
import by.VeranikaFiliptsova.quizer.tasks.TextTask;
import by.VeranikaFiliptsova.quizer.tasks.math.MathTask;
import org.w3c.dom.Text;

import java.util.*;

public class Main {

    static Map<String, Quiz> getQuizMap() {
        HashMap<String, Quiz> myQuizMap = new HashMap<>();
        EnumSet<MathTask.Operation> operationAllowed = EnumSet.allOf(MathTask.Operation.class);
        EnumSet<MathTask.Operation> operationAllowedDIV = EnumSet.of(MathTask.Operation.DIV);
        //test1: expression
        ExpressionMathTaskGenerator exprGen = new ExpressionMathTaskGenerator(-20, 20, operationAllowed);
        Quiz test1  =  new Quiz(exprGen, 10);
        myQuizMap.put("Test1", test1);
        //test2: equation
        EquationMathTaskGenerator eqvGen = new EquationMathTaskGenerator(-10, 10, operationAllowed);
        Quiz test2 = new Quiz(eqvGen, 10);
        myQuizMap.put("Test2", test2);
        //test3: text tasks and text expression
        TextTask text1 = new TextTask("Если 7 человек встретятся и все пожмут друг другу руку по одному разу," +
                " сколько рукопожатий произойдет? ", "21");
        TextTask text2 = new TextTask("Какая цифра чаще всего встречается между 1 и 1000 включительно? ", "1");
        TextTask text3 = new TextTask("Сказочному гному каждую ночь нужна одна свеча. Он может сделать 1 новую свечу из 5 огарков. " +
                "Если у него 25 огарков, на сколько ночей хватит? ", "6");
        TextTask text4 = new TextTask("Возможно ли, что позавчера Алисе было 5 лет, а в следующем году исполнится 8? ", "Да");
        PoolTaskGenerator pool1 = new PoolTaskGenerator(false, text1, text2, text3, text4);
        TextExpressionMathTaskGenerator textGen = new TextExpressionMathTaskGenerator(1, 20, operationAllowed);
        GroupTaskGenerator groupGen = new GroupTaskGenerator(textGen, pool1);
        Quiz test3 = new Quiz(groupGen, 10);
        myQuizMap.put("Test3", test3);
        //test4: everything
        ExpressionMathTaskGenerator exprGen2 = new ExpressionMathTaskGenerator(1, 100, operationAllowed);
        EquationMathTaskGenerator eqvGen2 = new EquationMathTaskGenerator(1, 100, operationAllowed);
        TextExpressionMathTaskGenerator textGen2 = new TextExpressionMathTaskGenerator(1, 100, operationAllowed);
        GroupTaskGenerator groupGen2 = new GroupTaskGenerator(exprGen2, eqvGen2, textGen2,pool1);
        Quiz test4 = new Quiz(groupGen2, 10);
        myQuizMap.put("Test4", test4);

        //test5: with division and exception
        ExpressionMathTaskGenerator exprGen5 = new ExpressionMathTaskGenerator(-10, 10, operationAllowedDIV);
        ExpressionMathTaskGenerator exprGenException = new ExpressionMathTaskGenerator(0, 0, operationAllowedDIV);
        EquationMathTaskGenerator eqvGen5 = new EquationMathTaskGenerator(-20, 20, operationAllowedDIV);
        TextExpressionMathTaskGenerator textGen5 = new TextExpressionMathTaskGenerator(-15, 15, operationAllowedDIV);
        GroupTaskGenerator groupGen5 = new GroupTaskGenerator(exprGen5, exprGenException, eqvGen5, textGen5);
        Quiz test5 = new Quiz(groupGen5, 10);
        myQuizMap.put("Test5", test5);
        return myQuizMap;

    }

    public static void main(String[] args) {
        Map<String, Quiz> quizzes = getQuizMap();
        Scanner console = new Scanner(System.in);
        String name;
        do {
            System.out.println("Введите название теста...");
            name = console.next();
        } while (!quizzes.containsKey(name));
        Quiz currentQuiz = quizzes.get(name);
        currentQuiz.execute();
        System.out.println(currentQuiz.getCorrectAnswerNumber() +" -верные ответы");
        System.out.println(currentQuiz.getWrongAnswerNumber() +" -неверные ответы");
        System.out.println("Ваша оценка - "+currentQuiz.getMark()+"/10.0");
    }
}