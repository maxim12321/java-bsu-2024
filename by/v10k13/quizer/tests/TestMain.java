package by.v10k13.quizer.tests;

import by.v10k13.quizer.Quiz;
import by.v10k13.quizer.Task;
import by.v10k13.quizer.exceptions.RunOutOfTasksException;
import by.v10k13.quizer.exceptions.TestNotFinishedYetException;
import by.v10k13.quizer.generators.GroupTaskGenerator;
import by.v10k13.quizer.generators.PoolTaskGenerator;
import by.v10k13.quizer.generators.math.AbstractMathTaskGenerator;
import by.v10k13.quizer.generators.math.ExpressionTaskGenerator;
import by.v10k13.quizer.tasks.TextTask;
import by.v10k13.quizer.tasks.math.ExpressionTask;
import by.v10k13.quizer.tasks.math.MathTask;
import by.v10k13.quizer.tests.tester.TestableGroup;
import by.v10k13.quizer.tests.tester.Tester;

import java.util.EnumSet;

public class TestMain {
    public static Quiz CreateSampleQuiz(int count) {
        return new Quiz(new ExpressionTaskGenerator(
                new AbstractMathTaskGenerator.MathTaskGeneratorConfig(
                        1,
                        10,
                        EnumSet.of(MathTask.Operators.DevOp,
                                MathTask.Operators.MulOp
                        ))), count);
    }

    @TestableGroup(Name="Math task validator test.")
    public static void MTTest(Tester tester_) {
        tester_.RunTest(tester->{
            var task = new ExpressionTask(10, 5, MathTask.Operators.DevOp);
            tester.Assert(task.validate("2") == Task.Result.OK);
            tester.Assert(task.validate("15") == Task.Result.WRONG);
            tester.Assert(task.validate("a") == Task.Result.INCORRECT_INPUT);
            tester.Assert(task.validate("15.2") == Task.Result.INCORRECT_INPUT);
        });
        tester_.RunTest(tester->{
            var task = new ExpressionTask(10, 5, MathTask.Operators.MulOp);
            tester.Assert(task.validate("50") == Task.Result.OK);
            tester.Assert(task.validate("15") == Task.Result.WRONG);
            tester.Assert(task.validate("a") == Task.Result.INCORRECT_INPUT);
            tester.Assert(task.validate("15.2") != Task.Result.INCORRECT_INPUT);
        });
    }

    @TestableGroup(Name="Text task validator test.")
    public static void TTTest(Tester tester_) {
        var task = new TextTask("How are you?", "OK");
        tester_.RunTest(tester->tester.Assert(task.validate("OK") == Task.Result.OK));
        tester_.RunTest(tester->tester.Assert(task.validate("BAD") == Task.Result.WRONG));
    }

    @TestableGroup(Name="Quiz tasks receive test.")
    public static void Q1Test(Tester tester_) {
        var q = CreateSampleQuiz(10);
        tester_.RunTest(tester->{
            for (int i = 0; i < 10; i++) {
                tester.AssertNoExceptions(q::nextTask);
                q.provideAnswer("-1");
            }
        });
        tester_.RunTest(tester->tester.Assert(q.isFinished()));
        tester_.RunTest(tester->tester.Assert(q.getMark() == 0));
    }

    @TestableGroup(Name="Quiz tasks receive same test.")
    public static void Q2Test(Tester tester_) {
        var q = CreateSampleQuiz(10);
        tester_.RunTest(tester->{
            for (int i = 0; i < 10; i++) {
                tester.AssertNoExceptions(() -> {
                    var task = q.nextTask();
                    q.provideAnswer("xDxDxDxDxDxDxDxDxDxDxDxDxDxD");
                    tester.Assert(q.nextTask() == task);
                });
                q.provideAnswer("-1");
            }
        });
        tester_.RunTest(tester->tester.Assert(q.isFinished()));
        tester_.RunTest(tester->tester.Assert(q.getMark() == 0));
    }

    @TestableGroup(Name="Quiz fails test.")
    public static void QTest(Tester tester_) {
        var q =  CreateSampleQuiz(1);
        tester_.RunTest(tester->tester.Assert(!q.isFinished()));
        tester_.RunTest(tester->tester.AssertException(q::getMark, TestNotFinishedYetException.class));
        tester_.RunTest(tester->tester.AssertNoExceptions(q::nextTask));
        tester_.RunTest(tester->tester.Assert(q.provideAnswer("-1") == Task.Result.WRONG));
        tester_.RunTest(tester->tester.AssertException(()->q.provideAnswer("-1"), RunOutOfTasksException.class));
        tester_.RunTest(tester->tester.AssertException(q::nextTask, RunOutOfTasksException.class));
    }

    @TestableGroup(Name="Pool generator test.")
    public static void P1Test(Tester tester_) {
        var task = new TextTask("How are you?", "OK");
        var pool = new PoolTaskGenerator(false, task);
        tester_.RunTest(tester->tester.AssertNoExceptions(pool::generate));
        tester_.RunTest(tester->tester.AssertException(pool::generate, RunOutOfTasksException.class));
    }

    @TestableGroup(Name="Pool deduplicated generator test.")
    public static void P2Test(Tester tester_) {
        var task = new TextTask("How are you?", "OK");
        var pool = new PoolTaskGenerator(false, task, task);
        tester_.RunTest(tester->tester.AssertNoExceptions(pool::generate));
        tester_.RunTest(tester->tester.AssertException(pool::generate, RunOutOfTasksException.class));
    }

    @TestableGroup(Name="Group generator first test.")
    public static void G1Test(Tester tester_) {
        var task = new TextTask("How are you?", "OK");
        var pool = new PoolTaskGenerator(true, task, task);
        var group = new GroupTaskGenerator(pool, pool);
        tester_.RunTest(tester->tester.AssertNoExceptions(group::generate));
        tester_.RunTest(tester->tester.AssertNoExceptions(group::generate));
        tester_.RunTest(tester->tester.AssertException(group::generate, RunOutOfTasksException.class));
    }

    @TestableGroup(Name="Group generator second test.")
    public static void G2Test(Tester tester_) {
        var task = new TextTask("How are you?", "OK");
        var pool1 = new PoolTaskGenerator(true, task);
        var pool2 = new PoolTaskGenerator(true, task);
        var pool3 = new PoolTaskGenerator(true, task);
        var group = new GroupTaskGenerator(pool1, pool2, pool3);
        tester_.RunTest(tester->tester.AssertNoExceptions(group::generate));
        tester_.RunTest(tester->tester.AssertNoExceptions(group::generate));
        tester_.RunTest(tester->tester.AssertNoExceptions(group::generate));
        tester_.RunTest(tester->tester.AssertException(group::generate, RunOutOfTasksException.class));
    }

    public static void main(String[] args) {
                Tester.RunGroups(TestMain.class);
    }
}
