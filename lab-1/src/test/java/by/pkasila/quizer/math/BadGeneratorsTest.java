package by.pkasila.quizer.math;

import by.pkasila.quizer.common.MathOperation;
import by.pkasila.quizer.exceptions.BadGeneratorException;
import by.pkasila.quizer.generators.JustForFunTaskGenerator;
import by.pkasila.quizer.generators.math.ExpressionTaskGenerator;
import by.pkasila.quizer.generators.math.VariantExpressionTaskGenerator;
import org.junit.jupiter.api.Test;

import java.util.EnumSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class BadGeneratorsTest {
    @Test
    void ExpressionTest() {
        try {
            new ExpressionTaskGenerator(0, -5, EnumSet.of(MathOperation.SUM));
            fail();
        } catch (BadGeneratorException e) {
            assertEquals(e.getMessage(), "min is greater than max value");
        }

        try {
            new ExpressionTaskGenerator(0, 0, EnumSet.of(MathOperation.DIVISION));
            fail();
        } catch (BadGeneratorException e) {
            assertEquals(e.getMessage(), "always division by zero");
        }

        try {
            new ExpressionTaskGenerator(0, 1, EnumSet.of(MathOperation.DIVISION));
        } catch (BadGeneratorException e) {
            fail();
        }
    }

    @Test
    void JustForFunTest() {
        try {
            new JustForFunTaskGenerator(0, -5, EnumSet.of(MathOperation.SUM));
            fail();
        } catch (BadGeneratorException e) {
            assertEquals(e.getMessage(), "min is greater than max value");
        }

        try {
            new JustForFunTaskGenerator(0, 0, EnumSet.of(MathOperation.DIVISION));
            fail();
        } catch (BadGeneratorException e) {
            assertEquals(e.getMessage(), "always division by zero");
        }

        try {
            new JustForFunTaskGenerator(0, 1, EnumSet.of(MathOperation.DIVISION));
        } catch (BadGeneratorException e) {
            fail();
        }
    }

    @Test
    void testVariantExpressionGenerator() {
        try {
            new VariantExpressionTaskGenerator(0, -9000, EnumSet.of(MathOperation.SUM));
            fail();
        } catch (BadGeneratorException e) {
            assertEquals(e.getMessage(), "min is greater than max value");
        }

        try {
            new VariantExpressionTaskGenerator(0, 0, EnumSet.of(MathOperation.DIVISION));
            fail();
        } catch (BadGeneratorException e) {
            assertEquals(e.getMessage(), "always division by zero");
        }

        try {
            new VariantExpressionTaskGenerator(0, 1, EnumSet.of(MathOperation.DIVISION));
            fail();
        } catch (BadGeneratorException e) {
            assertEquals(e.getMessage(), "always repeating variants");
        }

        try {
            new VariantExpressionTaskGenerator(0, 3, EnumSet.of(MathOperation.DIVISION));
        } catch (BadGeneratorException e) {
            fail();
        }
    }
}
