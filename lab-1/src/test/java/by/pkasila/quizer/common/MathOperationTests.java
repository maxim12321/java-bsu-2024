package by.pkasila.quizer.common;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MathOperationTests {
    @Test
    public void MathOperationSymbolTest() {
        assertEquals(MathOperation.SUM.getSymbol(), '+');
        assertEquals(MathOperation.DIFFERENCE.getSymbol(), '-');
        assertEquals(MathOperation.MULTIPLICATION.getSymbol(), '*');
        assertEquals(MathOperation.DIVISION.getSymbol(), '/');
    }
}
