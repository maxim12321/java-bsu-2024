import by.thekeenest.quizer.*;
import by.thekeenest.quizer.tasks.Task;
import by.thekeenest.quizer.tasks.TextTask;
import by.thekeenest.quizer.Main;
import by.thekeenest.quizer.tasks.math.MathTask;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;



public class QuizTest {

    private Map<String, Quiz> quizMap;

    @BeforeEach
    public void setUp() {
        quizMap = Main.getQuizMap(); // Assuming getQuizMap is static or modify as needed
    }

    @Test
    public void testMathQuizFlow() {
        Quiz mathQuiz = quizMap.get("group");
        assertNotNull(mathQuiz, "Math quiz should be initialized.");
        assertFalse(mathQuiz.isFinished(), "Math quiz should not be finished at start.");

        Task task1 = mathQuiz.getCurrentTask();
        assertNotNull(task1, "Task should be generated.");
        assertTrue(task1 instanceof MathTask, "Generated task should be a math task.");

        // Providing an answer
        Result result = mathQuiz.provideAnswer("3"); // Assuming 3 is an incorrect answer
        assertEquals(Result.WRONG, result, "The answer should be marked wrong.");

        mathQuiz.nextTask();
        assertFalse(mathQuiz.isFinished(), "Math quiz should still have tasks.");
    }

    @Test
    public void testPoolQuizFlow() {
        Quiz poolQuiz = quizMap.get("pool");
        assertNotNull(poolQuiz, "Pool quiz should be initialized.");
        assertFalse(poolQuiz.isFinished(), "Pool quiz should not be finished at start.");

        Task task = poolQuiz.getCurrentTask();
        assertNotNull(task, "Task should be generated.");

        // Providing a correct answer
        Result result = poolQuiz.provideAnswer("3"); // Replace with correct answer based on setup
        assertEquals(Result.OK, result, "The answer should be correct.");

        poolQuiz.nextTask();
        assertFalse(poolQuiz.isFinished(), "Pool quiz should still have tasks.");
    }

    @Test
    public void testTextQuizFlow() {
        Quiz textQuiz = quizMap.get("geography");
        assertNotNull(textQuiz, "Text quiz should be initialized.");
        assertTrue(textQuiz.getCurrentTask() instanceof Task, "The task should be a text-based question.");
        assertEquals("Столица России?", textQuiz.getCurrentTask().getText(), "The question should match expected text.");

        Result result = textQuiz.provideAnswer("Москва");
        assertEquals(Result.OK, result, "Answer should be correct for the geography quiz.");
        assertTrue(textQuiz.isFinished(), "Geography quiz should finish after one question.");
    }

    @Test
    public void testInvalidAnswerHandling() {
        Quiz textQuiz = quizMap.get("geography");
        assertNotNull(textQuiz, "Text quiz should be initialized.");

        Result result = textQuiz.provideAnswer("Париж");
        assertEquals(Result.WRONG, result, "Answer should be marked wrong.");

    }
}
