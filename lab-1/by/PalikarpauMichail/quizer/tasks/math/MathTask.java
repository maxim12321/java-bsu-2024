package by.PalikarpauMichail.quizer.tasks.math;

import by.PalikarpauMichail.quizer.Task;

public interface MathTask extends Task {
    enum Operation {
        ADDITION("+"),
        SUBTRACTION("-"),
        MULTIPLICATION("*"),
        DIVISON("/");

        private String name;

        Operation(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    static public Integer Calculate(
        Integer firstOperand,
        Integer secondOperand,
        Operation operation
    ) {
        switch(operation) {
            case ADDITION -> {
                return firstOperand + secondOperand;
            }
            case SUBTRACTION -> {
                return firstOperand - secondOperand;
            }
            case MULTIPLICATION -> {
                return firstOperand * secondOperand;
            }
            case DIVISON -> {
                return firstOperand / secondOperand;
            }
            default -> {
                return 0;
            }
        }
    }
}
