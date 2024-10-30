package by.SanchukS.quizer;

import by.SanchukS.quizer.exceptions.NullArgumentException;

/**
 * Класс-обёртка.
 * Хранит выражение вида "x op y = z", где op={+, -, *, /}.
 */
public class Expression {
    private final int[] numbers = new int[3];
    private final Operation operation;

    public Expression(int number1, Operation operation, int number2) {
        if (operation == null) throw new NullArgumentException("operation");
        this.numbers[0] = number1;
        this.numbers[1] = number2;
        this.operation = operation;
        this.numbers[2] = switch(operation.toString()) {
            case "+" -> number1 + number2;
            case "-" -> number1 - number2;
            case "*" -> number1 * number2;
            case "/" -> {
                if (number2 == 0) throw new IllegalArgumentException("Division by zero");
                if (number1 % number2 != 0) throw new IllegalArgumentException("Not integer division");
                yield number1 / number2;
            }
            default -> throw new IllegalArgumentException("Invalid operation");
        };
    }

    public int getNumber(int i) {
        if (i < 0 || i >= 3) throw new IndexOutOfBoundsException("Invalid index: " + i);
        return numbers[i];
    }

    public Operation getOperation() {
        return operation;
    }

    public static Expression of(int number1, Operation operation, int number2) {
        return new Expression(number1, operation, number2);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof Expression e)) return false;
        boolean result = operation.equals(e.getOperation());
        for (int i = 0; i < 3; ++i) {
            result &= (numbers[i] == e.getNumber(i));
        }
        return result;
    }
}
