package by.merinovvvv.quizer.tasks;

import by.merinovvvv.quizer.Result;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExpressionTaskTest {
    @Test
    void constructorTest() {
        ExpressionTask expressionTask = new ExpressionTask(2, 3, "+");
        assertNotNull(expressionTask);
    }

    @Test
    void getTextTest() {
        ExpressionTask expressionTask = new ExpressionTask(2, 3, "+");
        String text = expressionTask.getText();
        assertNotNull(text);
        assertEquals("2+3", text);
    }

    @Test
    void validateCorrectAnswerAdditionTest() {
        ExpressionTask expressionTask = new ExpressionTask(2, 3, "+");
        assertEquals(Result.OK, expressionTask.validate("5.0"));
    }

    @Test
    void validateWrongAnswerAdditionTest() {
        ExpressionTask expressionTask = new ExpressionTask(2, 3, "+");
        assertEquals(Result.WRONG, expressionTask.validate("6.0"));
    }

    @Test
    void validateCorrectAnswerSubtractionTest() {
        ExpressionTask expressionTask = new ExpressionTask(5, 3, "-");
        assertEquals(Result.OK, expressionTask.validate("2.0"));
    }

    @Test
    void validateWrongAnswerSubtractionTest() {
        ExpressionTask expressionTask = new ExpressionTask(5, 3, "-");
        assertEquals(Result.WRONG, expressionTask.validate("3.0"));
    }

    @Test
    void validateCorrectAnswerMultiplicationTest() {
        ExpressionTask expressionTask = new ExpressionTask(2, 3, "*");
        assertEquals(Result.OK, expressionTask.validate("6.0"));
    }

    @Test
    void validateWrongAnswerMultiplicationTest() {
        ExpressionTask expressionTask = new ExpressionTask(2, 3, "*");
        assertEquals(Result.WRONG, expressionTask.validate("7.0"));
    }

    @Test
    void validateCorrectAnswerDivisionTest() {
        ExpressionTask expressionTask = new ExpressionTask(6, 3, "/");
        assertEquals(Result.OK, expressionTask.validate("2.0"));
    }

    @Test
    void validateWrongAnswerDivisionTest() {
        ExpressionTask expressionTask = new ExpressionTask(6, 3, "/");
        assertEquals(Result.WRONG, expressionTask.validate("3.0"));
    }

    @Test
    void validateIncorrectInputTest() {
        ExpressionTask expressionTask = new ExpressionTask(2, 3, "+");
        assertEquals(Result.INCORRECT_INPUT, expressionTask.validate("invalid"));
    }
}