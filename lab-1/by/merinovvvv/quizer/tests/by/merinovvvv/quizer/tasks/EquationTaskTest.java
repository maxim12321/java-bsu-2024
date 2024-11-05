package by.merinovvvv.quizer.tasks;

import by.merinovvvv.quizer.Result;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Assertions;

class EquationTaskTest {
    @Test
    void constructorTest() {
        EquationTask equationTask = new EquationTask(2, 3, "+", 5);
        Assertions.assertNotNull(equationTask);
    }

    @Test
    void getTextTest() {
        EquationTask equationTask = new EquationTask(2, 3, "+", 5);
        String text = equationTask.getText();
        Assertions.assertNotNull(text);
        Assertions.assertEquals("2x+3=5", text);
    }

    @Test
    void validateCorrectAnswerAdditionTest() {
        EquationTask equationTask = new EquationTask(2, 3, "+", 5);
        Assertions.assertEquals(Result.OK, equationTask.validate("1.0"));
    }

    @Test
    void validateWrongAnswerAdditionTest() {
        EquationTask equationTask = new EquationTask(2, 3, "+", 5);
        Assertions.assertEquals(Result.WRONG, equationTask.validate("2.0"));
    }

    @Test
    void validateCorrectAnswerSubtractionTest() {
        EquationTask equationTask = new EquationTask(2, 3, "-", 5);
        Assertions.assertEquals(Result.OK, equationTask.validate("4.0"));
    }

    @Test
    void validateWrongAnswerSubtractionTest() {
        EquationTask equationTask = new EquationTask(2, 3, "-", 5);
        Assertions.assertEquals(Result.WRONG, equationTask.validate("3.0"));
    }

    @Test
    void validateCorrectAnswerMultiplicationTest() {
        EquationTask equationTask = new EquationTask(2, 3, "*", 6);
        Assertions.assertEquals(Result.OK, equationTask.validate("1.0"));
    }

    @Test
    void validateWrongAnswerMultiplicationTest() {
        EquationTask equationTask = new EquationTask(2, 3, "*", 6);
        Assertions.assertEquals(Result.WRONG, equationTask.validate("2.0"));
    }

    @Test
    void validateCorrectAnswerDivisionTest() {
        EquationTask equationTask = new EquationTask(2, 3, "/", 6);
        Assertions.assertEquals(Result.OK, equationTask.validate("9.0"));
    }

    @Test
    void validateWrongAnswerDivisionTest() {
        EquationTask equationTask = new EquationTask(2, 3, "/", 6);
        Assertions.assertEquals(Result.WRONG, equationTask.validate("8.0"));
    }

    @Test
    void validateIncorrectInputTest() {
        EquationTask equationTask = new EquationTask(2, 3, "+", 5);
        Assertions.assertEquals(Result.INCORRECT_INPUT, equationTask.validate("invalid"));
    }
}