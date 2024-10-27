package by.dilik14.quizer.tasks.math;

import by.dilik14.quizer.tasks.Task;

public interface MathTask extends Task {
    enum Operation {
        ADDITION,
        SUBTRACTION,
        MULTIPLICATION,
        DIVISION;

        public String GetSymbol() {
            return switch (this) {
                case ADDITION -> " + ";
                case SUBTRACTION -> " - ";
                case MULTIPLICATION -> " * ";
                case DIVISION -> " / ";
            };
        }

        public int Compute(int lhs, int rhs) {
            return switch (this) {
                case ADDITION -> lhs + rhs;
                case SUBTRACTION -> lhs - rhs;
                case MULTIPLICATION -> lhs * rhs;
                case DIVISION -> lhs / rhs;
            };
        }
    }

    int computeAnswer();
}
