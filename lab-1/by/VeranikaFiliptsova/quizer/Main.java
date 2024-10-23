package by.VeranikaFiliptsova.quizer;

import by.VeranikaFiliptsova.quizer.generators.GroupTaskGenerator;
import by.VeranikaFiliptsova.quizer.generators.PoolTaskGenerator;
import by.VeranikaFiliptsova.quizer.generators.math.EquationMathTaskGenerator;
import by.VeranikaFiliptsova.quizer.generators.math.ExpressionMathTaskGenerator;
import by.VeranikaFiliptsova.quizer.tasks.TextTask;
import by.VeranikaFiliptsova.quizer.tasks.math.EquationMathTask;
import by.VeranikaFiliptsova.quizer.tasks.math.ExpressionMathTask;
import by.VeranikaFiliptsova.quizer.tasks.math.MathTask;

import java.util.*;

public class Main {

    static Map<String, Quiz> getQuizMap() {
        HashMap<String, Quiz> myQuizMap = new HashMap<String, Quiz>();
        //test1
        EnumSet<MathTask.Operation> operationAllowed = EnumSet.allOf(MathTask.Operation.class);
        //EnumSet<MathTask.Operation> operationAllowed = EnumSet.of(MathTask.Operation.DIV);
        ExpressionMathTaskGenerator taskgen = new ExpressionMathTaskGenerator(-10, 10, operationAllowed);
        Quiz test1  =  new Quiz(taskgen, 10);
        myQuizMap.put("Test1", test1);
        //test2
        TextTask text1 = new TextTask("aaa? ", "aaa");
        TextTask text2 = new TextTask("bbb? ", "bbb");
        TextTask text3 = new TextTask("ccc? ", "ccc");
        PoolTaskGenerator pool1 = new PoolTaskGenerator(false, text1, text2, text3);
        ExpressionMathTaskGenerator exprGen = new ExpressionMathTaskGenerator(-15, 15, operationAllowed);
        EquationMathTaskGenerator eqvGen = new EquationMathTaskGenerator(-10, 10, operationAllowed);
        GroupTaskGenerator groupGen = new GroupTaskGenerator(eqvGen, exprGen, pool1);
        Quiz test2 = new Quiz(groupGen, 10);
        myQuizMap.put("Test2", test2);
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
        System.out.println("Ваша оценка - "+currentQuiz.getMark()+"/10.0"); //вывести результат

    }
}