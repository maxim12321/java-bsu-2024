package by.v10k13.quizer.tasks.math;

import by.v10k13.quizer.Task;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface MathTask extends Task {
    default String convertToParsable(String answer, boolean floating) {
        answer = answer.strip();
        if (!floating && answer.contains("."))
            return null;

        Pattern value_pattern = Pattern.compile("^[+-]?[0-9]+(?:\\.[0-9]*)?$");
        Matcher regex_matcher = value_pattern.matcher(answer);
        if (!regex_matcher.matches())
            return null;

        return answer;
    }

    enum Operators {
        SumOp('+') {
            @Override
            public double Function(double a, double b) {
                return a + b;
            }

            @Override
            public double InverseAFunction(double b, double answer) {
                return answer - b;
            }

            @Override
            public double InverseBFunction(double a, double answer) {
                return answer - a;
            }
        },
        SubOp('-') {
            @Override
            public double Function(double a, double b) {
                return a - b;
            }

            @Override
            public double InverseAFunction(double b, double answer) {
                return answer + b;
            }

            @Override
            public double InverseBFunction(double a, double answer) {
                return a - answer;
            }
        },
        MulOp('*') {
            @Override
            public double Function(double a, double b) {
                return a * b;
            }

            @Override
            public double InverseAFunction(double b, double answer) {
                return answer / b;
            }

            @Override
            public double InverseBFunction(double a, double answer) {
                return answer / a;
            }
        },
        DevOp('/') {
            @Override
            public double Function(double a, double b) {
                return a / b;
            }

            @Override
            public double InverseAFunction(double b, double answer) {
                return answer * b;
            }

            @Override
            public double InverseBFunction(double a, double answer) {
                return a / answer;
            }
        };
        public final char Symbol;
        Operators(char Symbol) { this.Symbol = Symbol; }
        public abstract double Function(double a, double b);
        public abstract double InverseAFunction(double b, double answer);
        public abstract double InverseBFunction(double a, double answer);

        @Override
        public String toString() {
            return Character.toString(Symbol);
        }
    }
}
