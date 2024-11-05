package by.merinovvvv.quizer.generators;

import by.merinovvvv.quizer.tasks.AppleTask;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AppleTaskGeneratorTest {
    @Test
    void appleGenerateTest() {
        AppleTaskGenerator appleTaskGenerator = new AppleTaskGenerator(2, 5);
        Assertions.assertInstanceOf(AppleTask.class, appleTaskGenerator.generate());
    }

    @Test
    void appleGeneratorCostrThrowsExceptionWhenMinGreaterThenMaxTest() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new AppleTaskGenerator(5, 2)
        );
        Assertions.assertEquals("minNumber can't be greater than maxNumber.", exception.getMessage());
    }
}