package by.KirillBukato.quizer.math;

import by.KirillBukato.quizer.Result;
import by.KirillBukato.quizer.tasks.math.EquationTask;
import static by.KirillBukato.quizer.tasks.math.MathOperation.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EquationTaskTest {

    @Test
    void BasicTest() {
        EquationTask equationTask = new EquationTask(5, ADD, 7, false);
        assertEquals(equationTask.getText(), "5 + x = 7");
        assertTrue(equationTask.isValid());
        assertEquals(equationTask.validate("2"), Result.OK);
    }

    @Test
    void InvalidTest() {
        {
            {
                EquationTask t = new EquationTask(0, MULTIPLY, 10, true);
                assertFalse(t.isValid());
            }
            {
                EquationTask t = new EquationTask(0, MULTIPLY, 10, false);
                assertFalse(t.isValid());
            }
            {
                EquationTask t = new EquationTask(10, MULTIPLY, 0, true);
                assertTrue(t.isValid());
            }
            {
                EquationTask t = new EquationTask(10, MULTIPLY, 0, false);
                assertTrue(t.isValid());
            }

            {
                EquationTask t = new EquationTask(0, DIVIDE, 10, true);
                assertFalse(t.isValid());
            }
            {
                EquationTask t = new EquationTask(0, DIVIDE, 10, false);
                assertFalse(t.isValid());
            }
            {
                EquationTask t = new EquationTask(10, DIVIDE, 0, true);
                assertTrue(t.isValid());
            }
            {
                EquationTask t = new EquationTask(10, DIVIDE, 0, false);
                assertFalse(t.isValid());
            }
        }
    }

    @Test
    void PrecisionTest() {
        EquationTask equationTask = new EquationTask(3, MULTIPLY, 1, true);
        assertEquals(equationTask.validate("0.3"), Result.WRONG);
        assertEquals(equationTask.validate("0.33"), Result.WRONG);
        assertEquals(equationTask.validate("0.333"), Result.OK);

        assertEquals(equationTask.validate("0.334"), Result.OK);
        assertEquals(equationTask.validate("0.332"), Result.WRONG);
        assertEquals(equationTask.validate("0.335"), Result.WRONG);
    }
}