package by.pkasila.quizer.common;

/**
 *  Enums with mathematical operations
 */
public enum MathOperation {
    SUM('+'),
    DIFFERENCE('-'),
    MULTIPLICATION('*'),
    DIVISION('/');

    MathOperation(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }

    private final char symbol;
}
