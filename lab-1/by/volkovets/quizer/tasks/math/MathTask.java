package by.volkovets.quizer.tasks.math;

import by.volkovets.quizer.Task;

public interface MathTask extends Task {
    enum Operation {
        Sum,
        Difference,
        Multiplication,
        Division
    }


    default boolean isDouble(String answer) {
        try {
            Double.parseDouble(answer);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
};
