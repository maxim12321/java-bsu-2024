package by.SanchukS.quizer;

public enum Operation {
    SUM,
    SUBTRACT,
    MULTIPLY,
    DIVIDE;

    @Override
    public String toString() {
        return switch(this) {
            case SUM -> "+";
            case SUBTRACT -> "-";
            case MULTIPLY -> "*";
            case DIVIDE -> "/";
        };
    }
}
