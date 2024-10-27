package by.pkasila.quizer.common;

import by.pkasila.quizer.exceptions.FinishedQuizException;
import by.pkasila.quizer.exceptions.NoTaskException;
import by.pkasila.quizer.exceptions.UnfinishedQuizException;
import by.pkasila.quizer.generators.PoolTaskGenerator;
import by.pkasila.quizer.generators.math.ExpressionTaskGenerator;
import by.pkasila.quizer.tasks.TextTask;
import by.pkasila.quizer.tasks.math.EquationTask;
import org.junit.jupiter.api.Test;

import java.util.EnumSet;

import static org.junit.jupiter.api.Assertions.*;

public class QuizTests {
    @Test
    public void NoTaskQuizTest() {
        Quiz quiz = new Quiz(
                new ExpressionTaskGenerator(
                        0,
                        10,
                        EnumSet.of(MathOperation.SUM)
                ), 5);

        assertFalse(quiz.isFinished());
        try {
            quiz.provideAnswer("0");
        } catch (Exception e) {
            assertEquals(e.getClass(), NoTaskException.class);
            assertEquals(e.getMessage(), "there is no task given at the moment");
        }
    }

    @Test
    public void UnfinishedQuizTest() {
        Quiz quiz = new Quiz(
                new ExpressionTaskGenerator(
                        0,
                        10,
                        EnumSet.of(MathOperation.SUM)
                ), 5);

        assertFalse(quiz.isFinished());
        try {
            quiz.getMark();
        } catch (Exception e) {
            assertEquals(e.getClass(), UnfinishedQuizException.class);
            assertEquals(e.getMessage(), "cannot get result yet");
        }
    }

    @Test
    public void QuizIncorrectInputTest() {
        Quiz quiz = new Quiz(
                new PoolTaskGenerator<EquationTask>(
                        true,
                        new EquationTask(1, MathOperation.SUM, 1, false)
                ), 5);

        quiz.nextTask();
        assertEquals(quiz.provideAnswer("x"), Result.INCORRECT_INPUT);
        quiz.nextTask();
        assertEquals(quiz.getIncorrectInputNumber(), 1);
    }

    @Test
    public void QuizMarkTest() {
        Quiz quiz = new Quiz(
                new PoolTaskGenerator<TextTask>(
                        true,
                        new TextTask("Test", "0")
                ), 5);

        for (int i = 0; i < 4; i++) {
            quiz.nextTask();
            quiz.provideAnswer("0");
        }
        quiz.nextTask();
        quiz.provideAnswer("1");
        assertTrue(quiz.isFinished());

        assertEquals(quiz.getMark(), 0.8);
    }

    @Test
    public void EmptyQuizMarkTest() {
        Quiz quiz = new Quiz(
                new PoolTaskGenerator<TextTask>(
                        true,
                        new TextTask("Test", "0")
                ), 0);

        assertTrue(quiz.isFinished());
        assertEquals(quiz.getMark(), 1);
    }

    @Test
    public void FinishedQuizNoTaskTest() {
        Quiz quiz = new Quiz(
                new ExpressionTaskGenerator(
                        0,
                        10,
                        EnumSet.of(MathOperation.SUM)
                ), 5);

        for (int i = 0; i < 5; i++) {
            quiz.nextTask();
            quiz.provideAnswer("0");
        }
        assertTrue(quiz.isFinished());
        try {
            quiz.nextTask();
        } catch (Exception e) {
            assertEquals(e.getClass(), FinishedQuizException.class);
            assertEquals(e.getMessage(), "you cannot request next task");
        }
    }

    @Test
    public void CompleteQuizTest() {
        Quiz quiz = new Quiz(
                new ExpressionTaskGenerator(
                        0,
                        10,
                        EnumSet.of(MathOperation.SUM)
                ), 5);

        for (int i = 0; i < 5; i++) {
            quiz.nextTask();
            quiz.provideAnswer("0");
        }
        assertEquals(quiz.getIncorrectInputNumber(), 0);
        assertTrue(quiz.isFinished());
    }
}
