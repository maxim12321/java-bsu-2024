package by.KirillBukato.quizer.math;

import by.KirillBukato.quizer.Result;
import by.KirillBukato.quizer.tasks.math.MathTask;
import by.KirillBukato.quizer.tasks.math.SimpleExpressionTask;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class SimpleExpressionTaskTest {
    @BeforeAll static void SetUp() {
        System.out.println("Math Tasks and Generators tests");
    }

    @Test
    void BasicTest() {
        SimpleExpressionTask simpleExpressionTask = new SimpleExpressionTask(5, MathTask.Operation.ADD, 7);
        Assertions.assertEquals(Result.OK, simpleExpressionTask.validate("12"));
        Assertions.assertEquals(simpleExpressionTask.getText(), "5 + 7 = ?");
        Assertions.assertTrue(simpleExpressionTask.isValid());
    }

    @Test
    void InvalidTest() {
        {
            SimpleExpressionTask simpleExpressionTask = new SimpleExpressionTask(5, MathTask.Operation.DIVIDE, 0);
            Assertions.assertFalse(simpleExpressionTask.isValid());
        }
    }

    @Test
    void PrecisionTest() {
        SimpleExpressionTask simpleExpressionTask = new SimpleExpressionTask(1, MathTask.Operation.DIVIDE, 3);
        Assertions.assertEquals(simpleExpressionTask.validate("0.3"), Result.WRONG);
        Assertions.assertEquals(simpleExpressionTask.validate("0.33"), Result.WRONG);
        Assertions.assertEquals(simpleExpressionTask.validate("0.333"), Result.OK);

        Assertions.assertEquals(simpleExpressionTask.validate("0.334"), Result.OK);
        Assertions.assertEquals(simpleExpressionTask.validate("0.332"), Result.WRONG);
        Assertions.assertEquals(simpleExpressionTask.validate("0.335"), Result.WRONG);
    }
}