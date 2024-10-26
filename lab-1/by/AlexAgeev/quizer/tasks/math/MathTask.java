package by.AlexAgeev.quizer.tasks.math;

import by.AlexAgeev.quizer.Task;

public interface MathTask extends Task{
    public enum Operators {
        ADD,
        SUB,
        DIV,
        MUL;
    }

    default String OperatorToString(Operators opperator) {
        String result = "";
        switch (opperator) {
            case ADD -> result = "+";
            case SUB -> result = "-";
            case DIV -> result = "/";
            default -> result = "*";
        }
        return result;
    }
}
