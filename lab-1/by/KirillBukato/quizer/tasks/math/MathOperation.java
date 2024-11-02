package by.KirillBukato.quizer.tasks.math;

import java.util.function.BiFunction;

public enum MathOperation {
    ADD('+',
            (left, right) -> left.doubleValue() + right.doubleValue(),
            (left, right) -> right.doubleValue() - left.doubleValue(),
            (left, right) -> right.doubleValue() - left.doubleValue()),
    SUBTRACT('-',
            (left, right) -> left.doubleValue() - right.doubleValue(),
            (left, right) -> left.doubleValue() + right.doubleValue(),
            (left, right) -> left.doubleValue() - right.doubleValue()),
    MULTIPLY('*',
            (left, right) -> left.doubleValue() * right.doubleValue(),
            (left, right) -> right.doubleValue() / left.doubleValue(),
            (left, right) -> right.doubleValue() / left.doubleValue()),
    DIVIDE('/',
            (left, right) -> left.doubleValue() / right.doubleValue(),
            (left, right) -> left.doubleValue() * right.doubleValue(),
            (left, right) -> left.doubleValue() / right.doubleValue());

    MathOperation(char symbol,
                  BiFunction<Integer, Integer, Double> expressionOperation,
                  BiFunction<Integer, Integer, Double> leftEquationOperation,
                  BiFunction<Integer, Integer, Double> rightEquationOperation) {
        this.symbol = symbol;
        this.expressionOperation = expressionOperation;
        this.leftEquationOperation = leftEquationOperation;
        this.rightEquationOperation = rightEquationOperation;
    }

    public char getSymbol() {
        return symbol;
    }

    public double applyExpression(int left, int right) {
        return expressionOperation.apply(left, right);
    }

    public double applyLeftEquation(int left, int right) {
        return leftEquationOperation.apply(left, right);
    }

    public double applyRightEquation(int left, int right) {
        return rightEquationOperation.apply(left, right);
    }

    private final char symbol;
    private final BiFunction<Integer, Integer, Double> expressionOperation;
    private final BiFunction<Integer, Integer, Double> leftEquationOperation;
    private final BiFunction<Integer, Integer, Double> rightEquationOperation;
}
