package by.KirillBukato.quizer.tasks.math;

import by.KirillBukato.quizer.Task;

public interface MathTask extends Task {
    enum Operation {
        ADD,
        SUBTRACT,
        MULTIPLY,
        DIVIDE
    }

    default String stringOperator(Operation operation) {
        return switch (operation) {
            case ADD -> "+";
            case SUBTRACT -> "-";
            case MULTIPLY -> "*";
            case DIVIDE -> "/";
        };
    }

    boolean isValid();

    double ComputeAnswer();
}
