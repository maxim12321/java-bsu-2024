package lab1.by.TyapkovArtem.quizer.tests;
import lab1.by.TyapkovArtem.quizer.*;
import lab1.by.TyapkovArtem.quizer.generators.math.AbstractMathTaskGenerator;
import lab1.by.TyapkovArtem.quizer.generators.math.ExpressionTaskGenerator;
import lab1.by.TyapkovArtem.quizer.tester.*;
import java.util.Scanner;

import java.util.*;

public class TestMain {
    public static  void main(String []args) {
        Tester.RunGroups(TestMain.class);
    }

    @TestableGroup(Name="Quiz fails test.")
    public static void QTest(Tester tester_) {
        tester_.RunTest(t -> t.Assert(2 == 2));
        tester_.RunTest(t -> t.Assert(2 == 3));
        Set<Operation> set = Collections.synchronizedSet(EnumSet.noneOf(Operation.class));
        set.add(Operation.Addition);
        set.add(Operation.Division);
        set.add(Operation.Multiplication);
        set.add(Operation.Subtraction);
        AbstractMathTaskGenerator.Config config = new AbstractMathTaskGenerator.Config(0, 20, (EnumSet<Operation>) set);
        ExpressionTaskGenerator taco4ku = new ExpressionTaskGenerator(config);
        Quiz quiz1 = new Quiz(taco4ku, 5);
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < 5; i++) {
            Task curtask = quiz1.nextTask();;
            System.out.println(curtask.getText());
            quiz1.provideAnswer(sc.next());
        }
        quiz1.getMark();
    }

    /**
     * @return тесты в {@link Map}, где
     * ключ     - название теста {@link String}
     * значение - сам тест       {@link Quiz}
     */
    /*static Map<String, Quiz> getQuizMap() {

    }*/
}

