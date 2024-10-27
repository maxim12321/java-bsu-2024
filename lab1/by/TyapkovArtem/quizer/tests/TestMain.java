package lab1.by.TyapkovArtem.quizer.tests;
import lab1.by.TyapkovArtem.quizer.*;
import lab1.by.TyapkovArtem.quizer.generators.GroupTaskGenerator;
import lab1.by.TyapkovArtem.quizer.generators.PoolTaskGenerator;
import lab1.by.TyapkovArtem.quizer.generators.math.AbstractMathTaskGenerator;
import lab1.by.TyapkovArtem.quizer.generators.math.EquationTaskGenerator;
import lab1.by.TyapkovArtem.quizer.generators.math.ExpressionTaskGenerator;
import lab1.by.TyapkovArtem.quizer.tester.*;
import java.util.*;

public class TestMain {
    public static  void main(String []args) {
        Tester.RunGroups(TestMain.class);
        Map<String, Quiz> map = new HashMap<>();
        map = getQuizMap();
        Quiz quiz = map.get("EquationTaskGenerator");
        Scanner sc = new Scanner(System.in);
        while (!quiz.isFinished()) {
            System.out.println(quiz.nextTask().getText());
            String str = sc.next();
            quiz.provideAnswer(str);
        }
        System.out.println(quiz.getMark());
    }

    @TestableGroup(Name="Quiz fails test.")
    public static void QTest1(Tester tester_) {
        tester_.RunTest(t -> {
            Set<Operation> set = Collections.synchronizedSet(EnumSet.noneOf(Operation.class));
            set.add(Operation.Addition);
            set.add(Operation.Division);
            set.add(Operation.Multiplication);
            set.add(Operation.Subtraction);
            AbstractMathTaskGenerator.Config config = new AbstractMathTaskGenerator.Config(0, 20, (EnumSet<Operation>) set);
            ExpressionTaskGenerator taco4ku = new ExpressionTaskGenerator(config);
            Quiz quiz1 = new Quiz(taco4ku, 5);
            for (int i = 0; i < 5; i++) {
                t.AssertNoExceptions(quiz1::nextTask);
            }
            t.AssertNoExceptions(quiz1::getMark);
        });
    }

    /**
     * @return тесты в {@link Map}, где
     * ключ     - название теста {@link String}
     * значение - сам тест       {@link Quiz}
     */
    static Map<String, Quiz> getQuizMap() {
        Map<String, Quiz> map = new HashMap<>();
        {
            EnumSet<Operation> set = EnumSet.allOf(Operation.class);
            AbstractMathTaskGenerator.Config config = new AbstractMathTaskGenerator.Config(1, 20, (EnumSet<Operation>) set);
            ExpressionTaskGenerator taco4ku = new ExpressionTaskGenerator(config);
            Quiz quiz1 = new Quiz(taco4ku, 5);
            map.put("ExpressionTaskGenerator", quiz1);
        }
        {
            EnumSet<Operation> set = EnumSet.allOf(Operation.class);
            AbstractMathTaskGenerator.Config config = new AbstractMathTaskGenerator.Config(1, 20, (EnumSet<Operation>) set);
            EquationTaskGenerator taco4ku = new EquationTaskGenerator(config);
            Quiz quiz2 = new Quiz(taco4ku, 5);
            map.put("EquationTaskGenerator", quiz2);
        }
        {
            EnumSet<Operation> set = EnumSet.allOf(Operation.class);
            AbstractMathTaskGenerator.Config config = new AbstractMathTaskGenerator.Config(1, 20, (EnumSet<Operation>) set);
            EquationTaskGenerator taco4ku = new EquationTaskGenerator(config);
            ExpressionTaskGenerator taco5ku = new ExpressionTaskGenerator(config);
            GroupTaskGenerator gr = new GroupTaskGenerator(taco4ku, taco5ku);
            Quiz quiz1 = new Quiz(gr, 5);
            map.put("GroupTaskGenerator", quiz1);
        }
        {
            EnumSet<Operation> set = EnumSet.allOf(Operation.class);
            AbstractMathTaskGenerator.Config config = new AbstractMathTaskGenerator.Config(1, 20, (EnumSet<Operation>) set);
            EquationTaskGenerator taco4ku = new EquationTaskGenerator(config);
            ExpressionTaskGenerator taco5ku = new ExpressionTaskGenerator(config);
            Task task = taco5ku.generate();
            PoolTaskGenerator gr = new PoolTaskGenerator(true, task, task, taco5ku.generate(), taco5ku.generate(), taco4ku.generate());
            Quiz quiz1 = new Quiz(gr, 5);
            map.put("PoolTaskGenerator", quiz1);
        }
        return map;
    }
}

