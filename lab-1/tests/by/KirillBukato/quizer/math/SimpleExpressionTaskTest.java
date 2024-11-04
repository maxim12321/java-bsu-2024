package by.KirillBukato.quizer.math;

import by.KirillBukato.quizer.Result;
import by.KirillBukato.quizer.tasks.math.MathOperation;
import by.KirillBukato.quizer.tasks.math.SimpleExpressionTask;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SimpleExpressionTaskTest {

    @Test
    void BasicTest() {
        SimpleExpressionTask simpleExpressionTask = new SimpleExpressionTask(5, MathOperation.ADD, 7);
        assertEquals(Result.OK, simpleExpressionTask.validate("12"));
        assertEquals(simpleExpressionTask.getText(), "5 + 7 = ?");
        assertTrue(simpleExpressionTask.isValid());
    }

    @Test
    void InvalidTest() {
        {
            SimpleExpressionTask simpleExpressionTask = new SimpleExpressionTask(5, MathOperation.DIVIDE, 0);
            assertFalse(simpleExpressionTask.isValid());
        }
    }

    @Test
    void PrecisionTest() {
        SimpleExpressionTask simpleExpressionTask = new SimpleExpressionTask(1, MathOperation.DIVIDE, 3);
        assertEquals(simpleExpressionTask.validate("0.3"), Result.WRONG);
        assertEquals(simpleExpressionTask.validate("0.33"), Result.WRONG);
        assertEquals(simpleExpressionTask.validate("0.333"), Result.OK);

        assertEquals(simpleExpressionTask.validate("0.334"), Result.OK);
        assertEquals(simpleExpressionTask.validate("0.332"), Result.WRONG);
        assertEquals(simpleExpressionTask.validate("0.335"), Result.WRONG);
    }
}