package by.SanchukS.quizer.generators;

import by.SanchukS.quizer.Expression;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Класс-генератор выражений вида "a op b = c", где op={+, -, *, /}
 */
public class ExpressionGenerator {
    private final int minNumber;
    private final int maxNumber;
    final List<String> operations;
    private final Random random = new Random();

    public ExpressionGenerator(
            int minNumber,
            int maxNumber,
            List<String> operations)
    {
        this.minNumber = minNumber;
        this.maxNumber = maxNumber;
        this.operations = new ArrayList<>(operations);
    }

    public Expression generateExpression() {
        final Random random = new Random();
        String operation = operations.get(random.nextInt(operations.size()));
        int firstNumber;
        int secondNumber;
        if (operation.equals("/")) {
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
}
