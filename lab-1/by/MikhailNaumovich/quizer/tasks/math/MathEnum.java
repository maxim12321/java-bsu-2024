package by.MikhailNaumovich.quizer.tasks.math;

public enum MathEnum {
    ADD('+'),
    SUBTRACT('-'),
    MULTIPLY('*'),
    DIVIDE('/');

    MathEnum(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }

    private final char symbol;
}
