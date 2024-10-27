package by.request3301.quizer;

import de.congrace.exp4j.Calculable;
import de.congrace.exp4j.ExpressionBuilder;
import de.congrace.exp4j.UnknownFunctionException;
import de.congrace.exp4j.UnparsableExpressionException;

public final class MathEngine {
    public static int eval(String expression) {
        try {
            Calculable calculable = new ExpressionBuilder(expression).build();
            return (int) calculable.calculate();
        } catch (UnparsableExpressionException | UnknownFunctionException e) {
            throw new RuntimeException(e);
        }
    }
}
