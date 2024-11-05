package by.merinovvvv.quizer.tasks;

import by.merinovvvv.quizer.Result;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TextTaskTest {
    @Test
    void constructorTest() {
        TextTask textTask = new TextTask("What is the capital of Belarus?", "Minsk");
        assertNotNull(textTask);
    }

    @Test
    void getTextTest() {
        TextTask textTask = new TextTask("What is the capital of Belarus?", "Minsk");
        String text = textTask.getText();
        assertNotNull(text);
        assertEquals("What is the capital of Belarus?", text);
    }

    @Test
    void validateCorrectAnswerTest() {
        TextTask textTask = new TextTask("What is the capital of Belarus?", "Minsk");
        assertEquals(Result.OK, textTask.validate("Minsk"));
    }

    @Test
    void validateWrongAnswerTest() {
        TextTask textTask = new TextTask("What is the capital of Belarus?", "Minsk");
        assertEquals(Result.WRONG, textTask.validate("Gomel"));
    }

    @Test
    void validateIncorrectInputTest() {
        TextTask textTask = new TextTask("What is the capital of Belarus?", "Minsk");
        assertEquals(Result.INCORRECT_INPUT, textTask.validate(""));
        assertEquals(Result.INCORRECT_INPUT, textTask.validate(null));
    }
}