package by.Alesia.quizer.tasks.math;

import by.Alesia.quizer.Task;

public interface MathTask extends Task {
    boolean IsValid();

    public enum Operation {
        ADD("+"),
        SUB("-"),
        MUL("*"),
        DIV("/");

        Operation(String s) {
            symbol = s;
        }


        public Operation ReverseOperation() {
            return switch (this) {
                case ADD -> SUB;
                case SUB -> ADD;
                case MUL -> DIV;
                case DIV -> MUL;
            };
        }

        public Integer MathOperation(Integer a, Integer b) {
            return switch (this) {
                case ADD -> a + b;
                case SUB -> a - b;
                case MUL -> a * b;
                case DIV -> a / b;
            };
        }

        public String GetSymbol() {
            return symbol;
        }

        final String symbol;
    }
}