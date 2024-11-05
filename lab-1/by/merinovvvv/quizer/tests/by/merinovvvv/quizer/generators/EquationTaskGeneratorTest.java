package by.merinovvvv.quizer.generators;

import by.merinovvvv.quizer.tasks.EquationTask;
import by.merinovvvv.quizer.tasks.math.MathTask;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EquationTaskGeneratorTest {
    @Test
    void equationGeneratorCostrThrowsExceptionWhenMinGreaterThenMaxTest() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new EquationTaskGenerator(5, 2, MathTask.Operation.MULTIPLICATION)
        );
        Assertions.assertEquals("minNumber can't be greater than maxNumber.", exception.getMessage());
    }

    @Test
    void equationGenerateTest() {
        EquationTaskGenerator equationTaskGenerator = new EquationTaskGenerator(1, 5, MathTask.Operation.MULTIPLICATION);
        Assertions.assertInstanceOf(EquationTask.class, equationTaskGenerator.generate());
    }

    @Test
    void getMinNumberTest() {
        EquationTaskGenerator equationTaskGenerator = new EquationTaskGenerator(1, 5, MathTask.Operation.MULTIPLICATION);
        Assertions.assertEquals(equationTaskGenerator.getMinNumber(), 1);
    }

    @Test
    void getMaxNumberTest() {
        EquationTaskGenerator equationTaskGenerator = new EquationTaskGenerator(1, 5, MathTask.Operation.MULTIPLICATION);
        Assertions.assertEquals(equationTaskGenerator.getMaxNumber(), 5);
    }
}