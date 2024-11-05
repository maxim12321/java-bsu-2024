package by.merinovvvv.quizer;

import by.merinovvvv.quizer.generators.GroupTaskGenerator;
import by.merinovvvv.quizer.tasks.TextTask;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    @Test
    void getQuizMapTest() {
        Map<String, Quiz> quizMap = Main.getQuizMap();
        // Check that the map is not null
        Assertions.assertNotNull(quizMap, "Quiz map should not be null");

        // Check that the map contains expected keys
        Assertions.assertTrue(quizMap.containsKey("expression sum"), "Quiz map should contain 'expression sum'");
        Assertions.assertTrue(quizMap.containsKey("equation sum"), "Quiz map should contain 'equation sum'");
        Assertions.assertTrue(quizMap.containsKey("group expression"), "Quiz map should contain 'group expression'");
        Assertions.assertTrue(quizMap.containsKey("group equation"), "Quiz map should contain 'group equation'");
        Assertions.assertTrue(quizMap.containsKey("group expression 2"), "Quiz map should contain 'group expression'");
        Assertions.assertTrue(quizMap.containsKey("group equation 2"), "Quiz map should contain 'group equation'");
        Assertions.assertTrue(quizMap.containsKey("pool expression"), "Quiz map should contain 'pool expression'");
        Assertions.assertTrue(quizMap.containsKey("pool equation"), "Quiz map should contain 'pool equation'");
        Assertions.assertTrue(quizMap.containsKey("capitals"), "Quiz map should contain 'capitals'");
        Assertions.assertTrue(quizMap.containsKey("apples"), "Quiz map should contain 'apples'");

        // Check that the quizzes are not null
        Assertions.assertNotNull(quizMap.get("expression sum"), "Quiz 'expression sum' should not be null");
        Assertions.assertNotNull(quizMap.get("equation sum"), "Quiz 'equation sum' should not be null");
        Assertions.assertNotNull(quizMap.get("group expression"), "Quiz 'group expression' should not be null");
        Assertions.assertNotNull(quizMap.get("group equation"), "Quiz 'group equation' should not be null");
        Assertions.assertNotNull(quizMap.get("group expression 2"), "Quiz 'group expression' should not be null");
        Assertions.assertNotNull(quizMap.get("group equation 2"), "Quiz 'group equation' should not be null");
        Assertions.assertNotNull(quizMap.get("pool expression"), "Quiz 'pool expression' should not be null");
        Assertions.assertNotNull(quizMap.get("pool equation"), "Quiz 'pool equation' should not be null");
        Assertions.assertNotNull(quizMap.get("capitals"), "Quiz 'capitals' should not be null");
        Assertions.assertNotNull(quizMap.get("apples"), "Quiz 'apples' should not be null");
    }

//    @Test
//    void getCapitalsTaskGeneratorTest() {
//        TaskGenerator<TextTask> generator = Main.getCapitalsTaskGenerator();
//
//        // Check that the generator is not null
//        Assertions.assertNotNull(generator, "Generator should not be null");
//
//        // Check that the generator is an instance of GroupTaskGenerator
//        assertInstanceOf(GroupTaskGenerator.class, generator, "Generator should be an instance of GroupTaskGenerator");
//    }
}