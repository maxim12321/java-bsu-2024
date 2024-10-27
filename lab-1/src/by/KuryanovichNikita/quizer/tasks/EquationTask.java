package by.KuryanovichNikita.quizer.tasks;

import by.KuryanovichNikita.quizer.tasks.math.AbstractMathTask;

public class EquationTask extends AbstractMathTask {


    public EquationTask(String text, int first, int second, String operator) {
        super(text, first, second, operator);
        if(second == 0)
            throw new IllegalArgumentException("a/x = 0");
        else if(first == 0 && second != 0)
            throw new IllegalArgumentException("0/x = a");
    }

    @Override
    public double compute() {
        switch (operator) {
            case "+":
                return (second - first);

            case "-":
                return (first - second);

            case "*":
                if (first == 0)
                    throw new ArithmeticException("Division by zero");
                else {
                    return ((double) second / first);
                }
            case "/":
                if (second == 0)
                    throw new ArithmeticException("Division by zero");
                else {
                    return ((double) first / second);
                }
            default:
                throw new IllegalStateException("Unexpected value: " + operator);
        }
    }
}