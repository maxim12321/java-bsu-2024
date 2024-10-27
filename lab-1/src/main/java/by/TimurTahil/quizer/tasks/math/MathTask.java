package by.TimurTahil.quizer.tasks.math;

import by.TimurTahil.quizer.Task;

public interface MathTask extends Task {
    public enum Operators {
        SUM,
        SUBTRACTION,
        DIVISION,
        MULTIPLICATION;
    }

    default String OperatorToString(Operators operator) {
        return switch (operator) {
            case SUM -> "+";
            case SUBTRACTION -> "-";
            case DIVISION -> "/";
            case MULTIPLICATION -> "*";
        };
    }
}
