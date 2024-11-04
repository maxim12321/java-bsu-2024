package by.KirillBukato.quizer.math;

import by.KirillBukato.quizer.exceptions.InvalidGeneratorException;
import by.KirillBukato.quizer.generators.math.*;
import by.KirillBukato.quizer.tasks.math.MathOperation;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.EnumSet;

public class InvalidGeneratorsTest {
    @Test
    void testSimpleExpressionGenerator() {
        try {
            new SimpleExpressionTaskGenerator(0, -5, EnumSet.of(MathOperation.ADD));
            fail();
        } catch (InvalidGeneratorException e) {
            assertEquals(e.getMessage(), "Min value is greater than Max value");
        }

        try {
            new SimpleExpressionTaskGenerator(0, 0, EnumSet.of(MathOperation.DIVIDE));
            fail();
        } catch (InvalidGeneratorException e) {
            assertEquals(e.getMessage(), "Task will always have zero division");
        }

        try {
            new SimpleExpressionTaskGenerator(0, 1, EnumSet.of(MathOperation.DIVIDE));
        } catch (InvalidGeneratorException e) {
            fail();
        }
    }

    @Test
    void testStoryExpressionGenerator() {
        try {
            new StoryExpressionTaskGenerator(0, -5, EnumSet.of(MathOperation.ADD));
            fail();
        } catch (InvalidGeneratorException e) {
            assertEquals(e.getMessage(), "Min value is greater than Max value");
        }

        try {
            new StoryExpressionTaskGenerator(0, 0, EnumSet.of(MathOperation.DIVIDE));
            fail();
        } catch (InvalidGeneratorException e) {
            assertEquals(e.getMessage(), "Task will always have zero division");
        }

        try {
            new StoryExpressionTaskGenerator(0, 1, EnumSet.of(MathOperation.DIVIDE));
        } catch (InvalidGeneratorException e) {
            fail();
        }
    }

    @Test
    void testVariantExpressionGenerator() {
        try {
            new VariantExpressionTaskGenerator(0, -5, EnumSet.of(MathOperation.ADD));
            fail();
        } catch (InvalidGeneratorException e) {
            assertEquals(e.getMessage(), "Min value is greater than Max value");
        }

        try {
            new VariantExpressionTaskGenerator(0, 0, EnumSet.of(MathOperation.DIVIDE));
            fail();
        } catch (InvalidGeneratorException e) {
            assertEquals(e.getMessage(), "Task will always have zero division");
        }

        try {
            new VariantExpressionTaskGenerator(0, 1, EnumSet.of(MathOperation.DIVIDE));
            fail();
        } catch (InvalidGeneratorException e) {
            assertEquals(e.getMessage(), "Task will always have repeating variants");
        }

        try {
            new VariantExpressionTaskGenerator(0, 3, EnumSet.of(MathOperation.DIVIDE));
        } catch (InvalidGeneratorException e) {
            fail();
        }
    }
}