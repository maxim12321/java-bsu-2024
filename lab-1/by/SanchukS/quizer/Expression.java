package by.SanchukS.quizer;

/**
 * Класс-обёртка.
 * Хранит выражение вида "x op y = z", где op={+, -, *, /}.
 */
public class Expression {
    private final int[] numbers = new int[3];
    private final String operation;

    public Expression(int number1, String operation, int number2) {
        if (operation == null) throw new IllegalArgumentException("Null operation");
        this.numbers[0] = number1;
        this.numbers[1] = number2;
        this.operation = operation;
        this.numbers[2] = switch(operation) {
            case "+" -> number1 + number2;
            case "-" -> number1 - number2;
            case "*" -> number1 * number2;
            case "/" -> {
                if (number2 == 0) throw new IllegalArgumentException("Division by zero");
                yield number1 / number2;
            }
            default -> throw new IllegalArgumentException("Invalid operation");
        };
    }

    public int getNumber(int i) {
        if (i < 0 || i >= 3) throw new IndexOutOfBoundsException("Invalid index: " + i);
        return numbers[i];
    }

    public String getOperation() {
        return operation;
    }

    public static Expression of(int number1, String operation, int number2) {
        return new Expression(number1, operation, number2);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof Expression e)) return false;
        boolean result = operation.equals(e.getOperation());
        for (int i = 0; i < 3; ++i) {
            result &= numbers[i] == e.getNumber(i);
        }
        return result;
    }
}
