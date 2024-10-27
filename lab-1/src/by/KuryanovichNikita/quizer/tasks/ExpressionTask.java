package by.KuryanovichNikita.quizer.tasks;

import by.KuryanovichNikita.quizer.Result;
import by.KuryanovichNikita.quizer.tasks.math.AbstractMathTask;

public class ExpressionTask extends AbstractMathTask {


    public ExpressionTask(String text, int first, int second, String operator) {
        super(text, first, second, operator);
        if(operator.equals("/") && second == 0)
            throw new IllegalArgumentException("a/0");
    }

    @Override
    public double compute() {
        switch(operator){
            case "+":
                return (first + second);

            case "-":
                return (first - second);

            case "*":
                return (first * second);

            case "/":
                    return ((double) first / second);

            default:
                throw new IllegalStateException("Unexpected value: " + operator);
        }
    }
}

