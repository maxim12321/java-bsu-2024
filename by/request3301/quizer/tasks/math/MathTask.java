package by.request3301.quizer.tasks.math;

import by.request3301.quizer.Task;

public interface MathTask extends Task {
    boolean checkEqual(String lhs, String rhs);

    enum Operation {
        ADDITION("+"),
        SUBTRACTION("-"),
        MULTIPLICATION("*"),
        DIVISION("/");

        private final String operation;

        Operation(String operation) {
            this.operation = operation;
        }

        public String getOperation() {
            return operation;
        }
    }
}
