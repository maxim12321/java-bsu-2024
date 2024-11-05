package by.merinovvvv.quizer.generators;

import by.merinovvvv.quizer.tasks.TextTask;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TextTaskGeneratorTest {

    @Test
    void constructorTest() {
        TextTaskGenerator textTaskGenerator = new TextTaskGenerator("What is the capital of Belarus?", "Minsk");
        Assertions.assertNotNull(textTaskGenerator);
    }

    @Test
    void generateTest() {
        TextTaskGenerator textTaskGenerator = new TextTaskGenerator("What is the capital of Belarus?", "Minsk");
        Assertions.assertInstanceOf(TextTask.class, textTaskGenerator.generate());
    }
}