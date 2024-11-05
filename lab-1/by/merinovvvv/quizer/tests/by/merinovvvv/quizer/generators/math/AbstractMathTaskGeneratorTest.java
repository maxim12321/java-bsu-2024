package by.merinovvvv.quizer.generators.math;

import by.merinovvvv.quizer.tasks.math.MathTask;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AbstractMathTaskGeneratorTest {
    @Test
    void generateMathTaskTest() {
        AbstractMathTaskGenerator generator = new AbstractMathTaskGenerator() {
            @Override
            public MathTask generate() {
                return null;
            }

            @Override
            public int getMinNumber() {
                return 1;
            }

            @Override
            public int getMaxNumber() {
                return 10;
            }
        };

        MathTask.Operation operation = MathTask.Operation.ADDITION;
        boolean monkey = false;

        Object[] result = generator.generateMathTask(generator.getMaxNumber(), generator.getMinNumber(), operation, monkey);

        // Check that the result array has the correct length
        Assertions.assertEquals(4, result.length);
        
        assertInstanceOf(Integer.class, result[0]);
        assertInstanceOf(Integer.class, result[1]);
        assertInstanceOf(String.class, result[2]);
        assertInstanceOf(Integer.class, result[3]);

        // Check that the operator is correct
        Assertions.assertEquals("+", result[2]);
    }

    @Test
    void getStringTest() {
        Assertions.assertEquals("+", AbstractMathTaskGenerator.getString(MathTask.Operation.ADDITION));
    }
}