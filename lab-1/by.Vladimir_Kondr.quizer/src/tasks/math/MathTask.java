package tasks.math;

import core.Task;

public interface MathTask extends Task {
    boolean isValid();


    enum Operation {
        ADDITION("+"),
        SUBTRACTION("-"),
        MULTIPLICATION("*"),
        DIVISION("/");

        private final String symbol;
        private static final int accuracy = 2;

        public static int getAccuracy() {
            return accuracy;
        }

        Operation(String symbol) {
            this.symbol = symbol;
        }

        public String getSymbol() {
            return symbol;
        }

        public int perform(Integer a, Integer b) {
            return switch (this) {
                case ADDITION -> (a + b) * (10 ^ accuracy);
                case SUBTRACTION -> (a - b) * (10 ^ accuracy);
                case MULTIPLICATION -> a * b * (10 ^ accuracy);
                case DIVISION -> {
                    if (b == 0) {
                        throw new ArithmeticException("Division by zero is not allowed");
                    }
                    yield ((a / b) * 10 ^ accuracy);
                }
            };
        }

        public Operation getOpposite () {
            return switch (this) {
                case ADDITION -> Operation.SUBTRACTION;
                case SUBTRACTION -> Operation.ADDITION;
                case MULTIPLICATION -> Operation.DIVISION;
                case DIVISION -> Operation.MULTIPLICATION;
            };
        }
    }
}
