package by.KirillBukato.quizer.math;

import by.KirillBukato.quizer.Result;
import by.KirillBukato.quizer.tasks.math.EquationTask;
import static by.KirillBukato.quizer.tasks.math.MathTask.Operation.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class EquationTaskTest {
    @BeforeAll static void SetUp() {
        System.out.println("Math Tasks and Generators tests");
    }

    @Test
    void BasicTest() {
        EquationTask equationTask = new EquationTask(5, ADD, 7, false);
        Assertions.assertEquals(equationTask.getText(), "5 + x = 7");
        Assertions.assertTrue(equationTask.isValid());
        Assertions.assertEquals(equationTask.validate("2"), Result.OK);
    }

    @Test
    void InvalidTest() {
        {
            {
                EquationTask t = new EquationTask(0, MULTIPLY, 10, true);
                Assertions.assertFalse(t.isValid());
            }
            {
                EquationTask t = new EquationTask(0, MULTIPLY, 10, false);
                Assertions.assertFalse(t.isValid());
            }
            {
                EquationTask t = new EquationTask(10, MULTIPLY, 0, true);
                Assertions.assertTrue(t.isValid());
            }
            {
                EquationTask t = new EquationTask(10, MULTIPLY, 0, false);
                Assertions.assertTrue(t.isValid());
            }

            {
                EquationTask t = new EquationTask(0, DIVIDE, 10, true);
                Assertions.assertFalse(t.isValid());
            }
            {
                EquationTask t = new EquationTask(0, DIVIDE, 10, false);
                Assertions.assertFalse(t.isValid());
            }
            {
                EquationTask t = new EquationTask(10, DIVIDE, 0, true);
                Assertions.assertTrue(t.isValid());
            }
            {
                EquationTask t = new EquationTask(10, DIVIDE, 0, false);
                Assertions.assertFalse(t.isValid());
            }
        }
    }

    @Test
    void PrecisionTest() {
        EquationTask equationTask = new EquationTask(3, MULTIPLY, 1, true);
        Assertions.assertEquals(equationTask.validate("0.3"), Result.WRONG);
        Assertions.assertEquals(equationTask.validate("0.33"), Result.WRONG);
        Assertions.assertEquals(equationTask.validate("0.333"), Result.OK);

        Assertions.assertEquals(equationTask.validate("0.334"), Result.OK);
        Assertions.assertEquals(equationTask.validate("0.332"), Result.WRONG);
        Assertions.assertEquals(equationTask.validate("0.335"), Result.WRONG);
    }
}