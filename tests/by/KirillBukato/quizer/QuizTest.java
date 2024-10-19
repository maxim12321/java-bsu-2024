package by.KirillBukato.quizer;

import by.KirillBukato.quizer.exceptions.QuizNotFinishedException;
import by.KirillBukato.quizer.generators.math.SimpleExpressionTaskGenerator;
import by.KirillBukato.quizer.tasks.math.MathTask;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.EnumSet;

public class QuizTest {

    @Test
    public void BasicTest() {
        Quiz quiz = new Quiz(
                new SimpleExpressionTaskGenerator(
                        0,
                        10,
                        EnumSet.of(MathTask.Operation.ADD)
                ), 5);

        for (int i = 0; i < 5; i++) {
            Assertions.assertFalse(quiz.isFinished());
            try {
                quiz.getMark();
            } catch (Exception e) {
                Assertions.assertEquals(e.getClass(), QuizNotFinishedException.class);
                Assertions.assertEquals(e.getMessage(), "Quiz is not finished, you can't get the result yet.");
            }
            quiz.nextTask();
            quiz.provideAnswer("0");
        }
        Assertions.assertTrue(quiz.isFinished());
    }

}
