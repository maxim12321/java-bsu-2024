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
        public static int pow10() {
            return (int) Math.pow(10, accuracy);
        }

        public static int getAccuracy() {
            return accuracy;
        }

        Operation(String symbol) {
            this.symbol = symbol;
        }

        public String getSymbol() {
            return symbol;
        }

        public int perform(Integer a, Integer b) throws ArithmeticException {
            return switch (this) {
                case ADDITION -> (a + b) * pow10();
                case SUBTRACTION -> (a - b) * pow10();
                case MULTIPLICATION -> a * b * pow10();
                case DIVISION -> {
                    if (b == 0) {
                        throw new ArithmeticException("Division by zero is not allowed");
                    }
                    yield (int) ((double) a / (double) b) * pow10();
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
