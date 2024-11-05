package by.merinovvvv.quizer.tasks;

import by.merinovvvv.quizer.Result;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Assertions;

class AppleTaskTest {
    @Test
    void constructorTest() {
        AppleTask appleTask = new AppleTask(1, 10);
        Assertions.assertNotNull(appleTask);
    }

    @Test
    void getTextTest() {
        AppleTask appleTask = new AppleTask(1, 10);
        String text = appleTask.getText();
        Assertions.assertNotNull(text);
        Assertions.assertTrue(text.contains("яблок"));
    }

    @Test
    void validateCorrectAnswerTest() {
        AppleTask appleTask = new AppleTask(1, 10);
        int num1 = appleTask.num1;
        int num2 = appleTask.num2;
        String correctAnswer = String.valueOf(num1 - num2);
        Assertions.assertEquals(Result.OK, appleTask.validate(correctAnswer));
    }

    @Test
    void validateWrongAnswerTest() {
        AppleTask appleTask = new AppleTask(1, 10);
        int num1 = appleTask.num1;
        int num2 = appleTask.num2;
        String wrongAnswer = String.valueOf(num1 - num2 + 1);
        Assertions.assertEquals(Result.WRONG, appleTask.validate(wrongAnswer));
    }

    @Test
    void validateIncorrectInputTest() {
        AppleTask appleTask = new AppleTask(1, 10);
        Assertions.assertEquals(Result.INCORRECT_INPUT, appleTask.validate("invalid"));
    }
}