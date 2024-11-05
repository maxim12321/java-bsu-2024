package by.merinovvvv.quizer.generators;

import by.merinovvvv.quizer.tasks.ExpressionTask;
import by.merinovvvv.quizer.tasks.math.MathTask;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExpressionTaskGeneratorTest {
    @Test
    void expressionGeneratorCostrThrowsExceptionWhenMinGreaterThenMaxTest() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new ExpressionTaskGenerator(5, 2, MathTask.Operation.MULTIPLICATION)
        );
        Assertions.assertEquals("minNumber can't be greater than maxNumber", exception.getMessage());
    }

    @Test
    void expressionGenerateTest() {
        ExpressionTaskGenerator expressionTaskGenerator = new ExpressionTaskGenerator(1, 5, MathTask.Operation.MULTIPLICATION);
        Assertions.assertInstanceOf(ExpressionTask.class, expressionTaskGenerator.generate());
    }

    @Test
    void getMinNumberTest() {
        ExpressionTaskGenerator expressionTaskGenerator = new ExpressionTaskGenerator(1, 5, MathTask.Operation.MULTIPLICATION);
        Assertions.assertEquals(expressionTaskGenerator.getMinNumber(), 1);
    }

    @Test
    void getMaxNumberTest() {
        ExpressionTaskGenerator expressionTaskGenerator = new ExpressionTaskGenerator(1, 5, MathTask.Operation.MULTIPLICATION);
        Assertions.assertEquals(expressionTaskGenerator.getMaxNumber(), 5);
    }
}