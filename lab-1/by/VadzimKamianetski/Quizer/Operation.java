package by.VadzimKamianetski.Quizer;

public enum Operation {
    GENERATESUM('+'), // разрешить генерацию с оператором +
    GENERATEDIFFERENCE('-'), // разрешить генерацию с оператором -
    GENERATEMULTIPLICATION('*'), // разрешить генерацию с оператором *
    GENERATEDIVISION('/'); // разрешить генерацию с оператором /

    Operation(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }

    private final char symbol;
}