package by.SanchukS.quizer;

import java.util.EnumSet;

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

    public static EnumSet<Operation> all() {
        return EnumSet.allOf(Operation.class);
    }
}
