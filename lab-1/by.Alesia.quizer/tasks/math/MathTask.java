package by.Alesia.quizer.tasks.math;

import by.Alesia.quizer.Task;
import by.Alesia.quizer.exceptions.DivisionByZero;

import java.util.Vector;

public interface MathTask extends Task {
    boolean IsValid();

    public enum Operation {
        ADD("+"),
        SUB("-"),
        MUL("*"),
        DIV("/");

        Operation(String s) {
            symbol =  s;
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
                case DIV -> {
                    if (b == 0) {
                        throw new DivisionByZero("Division by zero is prohibited");
                    }
                    yield a / b;
                }
            };
        }

        public String GetSymbol() {
            return symbol;
        }

        private final String symbol;
    }
}