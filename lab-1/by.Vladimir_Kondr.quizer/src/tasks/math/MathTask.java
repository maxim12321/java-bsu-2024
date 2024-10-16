package tasks.math;

import core.Task;

import java.util.Random;

public interface MathTask extends Task {
    boolean isValid();
    enum Operation {
        ADDITION("+"),
        SUBTRACTION("-"),
        MULTIPLICATION("*"),
        DIVISION("/");

        private final String symbol;
        private static final Operation[] operations = values();

        Operation(String symbol) {
            this.symbol = symbol;
        }

        public String getSymbol() {
            return symbol;
        }

        public Integer perform(Integer a, Integer b) {
            return switch (this) {
                case ADDITION -> a + b;
                case SUBTRACTION -> a - b;
                case MULTIPLICATION -> a * b;
                case DIVISION -> {
                    if (b == 0) {
                        throw new ArithmeticException("Division by zero is not allowed");
                    }
                    yield a / b;
                }
            };
        }

        public static Operation pickRandom(Random rand) {
            return operations[rand.nextInt(operations.length)];
        }

        public static Operation[] createArrayFromFlags(boolean... flags) {
            return java.util.Arrays.stream(Operation.values())
                    .filter(option -> flags[option.ordinal()])
                    .toArray(Operation[]::new);
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
