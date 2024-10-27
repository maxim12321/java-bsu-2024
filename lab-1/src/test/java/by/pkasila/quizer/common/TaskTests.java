package by.pkasila.quizer.common;

import by.pkasila.quizer.tasks.TextTask;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTests {
    @Test
    public void testTextTask() {
        TextTask task = new TextTask("Example", "abcdef123");

        assertEquals(task.getText(), "Example");
        assertEquals(task.validate("abc"), Result.WRONG);
        assertEquals(task.validate("abcdef123"), Result.OK);
    }
}
