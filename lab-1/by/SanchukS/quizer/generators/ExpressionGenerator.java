package by.SanchukS.quizer.generators;

import by.SanchukS.quizer.Expression;
import by.SanchukS.quizer.Operation;
import by.SanchukS.quizer.exceptions.NullArgumentException;

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
        if (minNumber > maxNumber) throw new IllegalArgumentException("minNumber > maxNumber");
        if (operations == null) throw new NullArgumentException("operations");
        this.minNumber = minNumber;
        this.maxNumber = maxNumber;
        this.operations = operations;
    }

    public Expression generate() {
        Operation operation = operations.stream()
                .reduce((op1, op2) -> random.nextBoolean() ? op1 : op2)
                .orElseThrow(() -> new IllegalArgumentException("Zero operations available"));
        int firstNumber;
        int secondNumber;
        if (operation.toString().equals("/")) {
            firstNumber = random.nextInt(maxNumber - minNumber) + minNumber;
            List<Integer> divisors = new ArrayList<>();

            for (int i = minNumber; i < firstNumber; ++i) {
                if (firstNumber % i == 0 && i != 1)
                    divisors.add(i);
            }

            if (divisors.isEmpty())
                secondNumber = 1;
            else
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
