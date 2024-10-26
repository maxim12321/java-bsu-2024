package by.v10k13.quizer.tests;

import by.v10k13.quizer.Quiz;
import by.v10k13.quizer.Task;
import by.v10k13.quizer.generators.math.AbstractMathTaskGenerator;
import by.v10k13.quizer.generators.math.ExpressionTaskGenerator;
import by.v10k13.quizer.tasks.TextTask;
import by.v10k13.quizer.tasks.math.ExpressionTask;
import by.v10k13.quizer.tasks.math.MathTask;
import by.v10k13.quizer.tests.tester.TestableGroup;
import by.v10k13.quizer.tests.tester.Tester;

import java.util.EnumSet;
import java.util.Scanner;

public class TestMain {

    @TestableGroup(Name="Math task validator test.")
    public static void MTTest(Tester tester_) {
        tester_.RunTest(tester->{
            var task = new ExpressionTask(10, 5, MathTask.Operators.DevOp);
            tester.Assert(task.validate("2") == Task.Result.OK);
            tester.Assert(task.validate("15") == Task.Result.WRONG);
            tester.Assert(task.validate("a") == Task.Result.INCORRECT_INPUT);
            tester.Assert(task.validate("15.2") == Task.Result.INCORRECT_INPUT);
            tester.Assert(task.Precision() == 0.3);
        });
        tester_.RunTest(tester->{
            var task = new ExpressionTask(10, 5, MathTask.Operators.MulOp);
            tester.Assert(task.validate("50") == Task.Result.OK);
            tester.Assert(task.validate("15") == Task.Result.WRONG);
            tester.Assert(task.validate("a") == Task.Result.INCORRECT_INPUT);
            tester.Assert(task.validate("15.2") == Task.Result.INCORRECT_INPUT);
            tester.Assert(task.Precision() != 0.3);
        });
    }

    @TestableGroup(Name="Text task validator test.")
    public static void TTTest(Tester tester_) {
        tester_.RunTest(tester->{
            var task = new TextTask("How are you?", "OK");
            tester.Assert(task.validate("OK") == Task.Result.OK);
            tester.Assert(task.validate("BAD") == Task.Result.WRONG);
        });
    }

    @TestableGroup(Name="Quiz tasks receive test.")
    public static void QTest(Tester tester_) {
        var q = new Quiz(
                new ExpressionTaskGenerator(
                        new AbstractMathTaskGenerator.MathTaskGeneratorConfig(
                                1,
                                10,
                                EnumSet.of(MathTask.Operators.DevOp,
                                        MathTask.Operators.MulOp
                                ))),
                10);
        tester_.RunTest(tester->{
            for (int i = 0; i < 10; i++) {
                tester.AssertNoExceptions(() -> {
                    q.nextTask();
                });
                q.provideAnswer("1");
            }
            tester.Assert(q.isFinished());
        });
    }

    public static void main(String[] args) {
        Tester.RunGroups(TestMain.class);
    }
}
