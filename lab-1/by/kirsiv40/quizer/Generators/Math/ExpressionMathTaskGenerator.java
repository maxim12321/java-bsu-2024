package by.kirsiv40.quizer.Generators.Math;

import java.util.EnumSet;
import java.util.concurrent.ThreadLocalRandom;

import by.kirsiv40.quizer.Generators.Math.Exceptions.BadNumericRangeException;
import by.kirsiv40.quizer.Generators.Math.Exceptions.PoolEmptyException;
import by.kirsiv40.quizer.Tasks.Math.ExpressionMathTask;
import by.kirsiv40.quizer.Tasks.Math.MathTask.Operation;

public class ExpressionMathTaskGenerator extends AbstractMathTaskGenerator<ExpressionMathTask> {
    private final EnumSet<Operation> symbols;

    public ExpressionMathTaskGenerator(
        int minNumber,
        int maxNumber,
        EnumSet<Operation> symbols
    ) {
        this.maxNumber = maxNumber;
        this.minNumber = minNumber;
        this.symbols = symbols;
    }

    @Override
    public ExpressionMathTask generate() throws BadNumericRangeException, PoolEmptyException {
        int a = generateNumber();
        int b = generateNumber();
        Operation op = symbols.toArray(new Operation[0])[ThreadLocalRandom.current().nextInt(symbols.size())];
        if (op == Operation.PLUS) {

            return new ExpressionMathTask("" + a + " + " + b + " = ", a + b);
        }
        if (op == Operation.MINUS) {
            return new ExpressionMathTask("" + a + " - " + b + " = ", a - b);
        }
        if (op == Operation.PROD) {
            return new ExpressionMathTask("" + a + " * " + b + " = ", a * b);
        }
        if (op == Operation.DIV) {
            b = generateNonZero();
            return new ExpressionMathTask("" + a + " / " + b + " = ", a / b);
        }
        throw new PoolEmptyException("No valid operations in ExprMathTaskGenerator");
    }
}
