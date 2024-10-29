package by.SanchukS.quizer.generators;

import by.SanchukS.quizer.Expression;
import by.SanchukS.quizer.Operation;

import java.util.*;

/**
 * Класс-генератор выражений вида "a op b = c", где op={+, -, *, /}
 */
public class ExpressionGenerator {
    private final int minNumber;
    private final int maxNumber;
    private final EnumSet<Operation> operations;
    private final Random random = new Random();

    public ExpressionGenerator(
            int minNumber,
            int maxNumber,
            EnumSet<Operation> operations)
    {
        this.minNumber = minNumber;
        this.maxNumber = maxNumber;
        this.operations = operations;
    }

    public Expression generateExpression() {
        final Random random = new Random();
        Operation operation = operations.stream()
                .reduce((op1, op2) -> random.nextBoolean() ? op1 : op2)
                .orElseThrow();
        int firstNumber;
        int secondNumber;
        if (operation.toString().equals("/")) {
            firstNumber = random.nextInt(maxNumber - minNumber) + minNumber;
            List<Integer> divisors = new ArrayList<>();
            divisors.add(firstNumber);

            int buffer = firstNumber;
            for (int i = 1; i * 2 <= buffer; ++i) {
                if (buffer % i == 0 && i > minNumber) divisors.add(i);
            }

            secondNumber = divisors.get(random.nextInt(divisors.size()));
        } else {
            firstNumber = random.nextInt(maxNumber - minNumber) + minNumber;
            secondNumber = random.nextInt(maxNumber - minNumber) + minNumber;
        }
        return Expression.of(firstNumber, operation, secondNumber);
    }

    public int getMaxNumber() {return maxNumber;}
    public int getMinNumber() {return minNumber;}
}
