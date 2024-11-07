package by.pkasila.quizer.math;

import by.pkasila.quizer.common.MathOperation;
import by.pkasila.quizer.common.Result;
import by.pkasila.quizer.tasks.math.EquationTask;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EquationTaskTest {

    @Test
    void EquationBasicTest() {
        EquationTask equationTask = new EquationTask(5, MathOperation.SUM, 7, false);
        assertEquals(equationTask.getText(), "5 + x = 7");
        assertFalse(equationTask.isInvalid());
        assertEquals(equationTask.validate("2"), Result.OK);
    }

    @Test
    void InvalidEquationTest() {
        {
            {
                EquationTask t = new EquationTask(0, MathOperation.MULTIPLICATION, 10, true);
                assertTrue(t.isInvalid());
            }
            {
                EquationTask t = new EquationTask(0, MathOperation.MULTIPLICATION, 10, false);
                assertTrue(t.isInvalid());
            }
            {
                EquationTask t = new EquationTask(10, MathOperation.MULTIPLICATION, 0, true);
                assertFalse(t.isInvalid());
            }
            {
                EquationTask t = new EquationTask(10, MathOperation.MULTIPLICATION, 0, false);
                assertFalse(t.isInvalid());
            }

            {
                EquationTask t = new EquationTask(0, MathOperation.DIVISION, 10, true);
                assertTrue(t.isInvalid());
            }
            {
                EquationTask t = new EquationTask(0, MathOperation.DIVISION, 10, false);
                assertTrue(t.isInvalid());
            }
            {
                EquationTask t = new EquationTask(10, MathOperation.DIVISION, 0, true);
                assertFalse(t.isInvalid());
            }
            {
                EquationTask t = new EquationTask(10, MathOperation.DIVISION, 0, false);
                assertTrue(t.isInvalid());
            }
        }
    }

    @Test
    void EquationPrecisionTest() {
        EquationTask equationTask = new EquationTask(3, MathOperation.MULTIPLICATION, 2, true);
        assertEquals(equationTask.validate("0.6"), Result.WRONG);
        assertEquals(equationTask.validate("0.66"), Result.WRONG);
        assertEquals(equationTask.validate("0.666"), Result.OK);

        assertEquals(equationTask.validate("0.667"), Result.OK);
        assertEquals(equationTask.validate("0.665"), Result.WRONG);
        assertEquals(equationTask.validate("0.668"), Result.WRONG);
    }
}
