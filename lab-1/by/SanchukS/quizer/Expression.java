package by.SanchukS.quizer;

/**
 * Класс-обёртка.
 * Хранит выражение вида "x op y = z", где op={+, -, *, /}.
 */
public class Expression {
    int[] numbers = new int[3];
    String operation;

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
}
