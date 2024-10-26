package by.kirsiv40.quizer.Generators.Math;

import java.util.EnumSet;
import java.util.concurrent.ThreadLocalRandom;

import by.kirsiv40.quizer.Generators.Math.Exceptions.BadNumericRangeException;
import by.kirsiv40.quizer.Generators.Math.Exceptions.PoolEmptyException;
import by.kirsiv40.quizer.Tasks.Math.EquationMathTask;
import by.kirsiv40.quizer.Tasks.Math.MathTask.Operation;

public class EquationMathTaskGenerator extends AbstractMathTaskGenerator<EquationMathTask> {
    private final EnumSet<Operation> symbols;

    public EquationMathTaskGenerator(
        int minNumber,
        int maxNumber,
        EnumSet<Operation> symbols
    ) {
        this.maxNumber = maxNumber;
        this.minNumber = minNumber;
        this.symbols = symbols;
    }

    public EquationMathTask generate() throws BadNumericRangeException, PoolEmptyException {
        int t = ThreadLocalRandom.current().nextInt(0, 2);
        int a;
        int b;
        Operation op = symbols.toArray(new Operation[0])[ThreadLocalRandom.current().nextInt(symbols.size())];
        if (op == Operation.PLUS) {
            a = generateNumber();
            b = generateNumber();
            if (t == 0) {
                return new EquationMathTask("" + a + " + x = " + b, b - a);
            } else {
                return new EquationMathTask("x + " + a + " = " + b, b - a);
            }
        }
        if (op == Operation.MINUS) {
            a = generateNumber();
            b = generateNumber();
            if (t == 0) {
                return new EquationMathTask("" + a + " - x = " + b, a - b);
            } else {
                return new EquationMathTask("x - " + a + " = " + b, b + a);
            }
        }
        if (op == Operation.PROD) {
            a = generateNonZero();
            b = generateNumber();
            if (t == 0) {
                return new EquationMathTask("" + a + " * x = " + b, b / a);
            } else {
                return new EquationMathTask("x * " + a + " = " + b, b / a);
            }
        }
        if (op == Operation.DIV) {
            if (t == 0) {
                a = generateNonZero();
                b = generateNonZero();
                return new EquationMathTask("" + a + " / x = " + b, a / b);
            } else {
                a = generateNonZero();
                b = generateNumber();
                return new EquationMathTask("x / " + a + " = " + b, b * a);
            }
        }
        throw new PoolEmptyException("No valid operations in EqMathTaskGenerator");
    }
}
