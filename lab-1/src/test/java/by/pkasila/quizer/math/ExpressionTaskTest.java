package by.pkasila.quizer.math;

import by.pkasila.quizer.common.MathOperation;
import by.pkasila.quizer.common.Result;
import by.pkasila.quizer.tasks.math.ExpressionTask;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ExpressionTaskTest {

    @Test
    void BasicTest() {
        ExpressionTask simpleExpressionTask = new ExpressionTask(5, MathOperation.SUM, 7);
        assertEquals(Result.OK, simpleExpressionTask.validate("12"));
        assertEquals(simpleExpressionTask.getText(), "5 + 7 = ?");
        assertFalse(simpleExpressionTask.isInvalid());
    }

    @Test
    void InvalidTest() {
        {
            ExpressionTask simpleExpressionTask = new ExpressionTask(5, MathOperation.DIVISION, 0);
            assertTrue(simpleExpressionTask.isInvalid());
        }
    }

    @Test
    void PrecisionTest() {
        ExpressionTask simpleExpressionTask = new ExpressionTask(2, MathOperation.DIVISION, 3);
        assertEquals(simpleExpressionTask.validate("0.6"), Result.WRONG);
        assertEquals(simpleExpressionTask.validate("0.66"), Result.WRONG);
        assertEquals(simpleExpressionTask.validate("0.666"), Result.OK);

        assertEquals(simpleExpressionTask.validate("0.667"), Result.OK);
        assertEquals(simpleExpressionTask.validate("0.668"), Result.WRONG);
        assertEquals(simpleExpressionTask.validate("0.665"), Result.WRONG);
    }
}
