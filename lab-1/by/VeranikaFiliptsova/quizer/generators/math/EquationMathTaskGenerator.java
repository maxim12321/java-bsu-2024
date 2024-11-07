package by.VeranikaFiliptsova.quizer.generators.math;

import by.VeranikaFiliptsova.quizer.exceptions.GeneratorNotValidException;
import by.VeranikaFiliptsova.quizer.tasks.math.MathTask.Operation;
import by.VeranikaFiliptsova.quizer.tasks.math.EquationMathTask;

import java.util.EnumSet;
import java.util.Random;

import static java.lang.Math.abs;

public class EquationMathTaskGenerator extends AbstractMathTaskGenerator<EquationMathTask>{
    /**
     * @param minNumber              минимальное число
     * @param maxNumber              максимальное число
     * @param operations            разрешить генерацию с операторами, перечисленными в {@link EnumSet}
     */

    public EquationMathTaskGenerator(
            int minNumber,
            int maxNumber,
            EnumSet<Operation> operations
    ) {
        super(minNumber, maxNumber, operations);
    }

    /**
     * return задание типа {@link EquationMathTask}
     */
    public EquationMathTask generate() {
        if ( isNotValid()) {
            throw new GeneratorNotValidException("Set of allowed operations is empty or minimum value is greater than maximum value");
        }
        if (maxNumb == 0 && minNumb == 0
                && !operationAllowed.contains(Operation.SUM)
                && !operationAllowed.contains(Operation.DIFF)) {
            throw new GeneratorNotValidException("impossible to generate task without division on 0");
        }
        Random rand = new Random();
        int n1;
        int n2;
        boolean xStart = rand.nextBoolean();
        Operation op = Operation.SUM;
        int opIndex = rand.nextInt(operationAllowed.size());
        int i = 0;
        for (Operation element : operationAllowed) {
            if (i == opIndex) {
                op = element;
            }
            i++;
        }

        if (op.equals(Operation.MUL)) {
            do {
                n1 = randUsual();
            } while (n1 == 0);
            if (n1 > 0) {
                n2 = n1 * ((minNumb + n1 - 1)/n1 + rand.nextInt((maxNumb - minNumb)/n1 + 1));
            } else {
                n2 = n1 * ((maxNumb + n1 - 1)/n1 + rand.nextInt((minNumb - maxNumb)/n1 + 1));
            }

        } else if (op.equals(Operation.DIV) && !xStart) {
            do {
                n2 = randUsual();
            } while (n2 == 0);
            do {
                if (n2 > 0) {
                    n1 = n2 * ((minNumb + n2 - 1)/n2 + rand.nextInt((maxNumb - minNumb)/n2 + 1));
                } else {
                    n1 = n2 * ((maxNumb + n2 - 1)/n2 + rand.nextInt((minNumb - maxNumb)/n2 + 1));
                }
            } while (n1 == 0);

        } else if (op.equals(Operation.DIV)) {
            do {
                n1 = randUsual();
            } while (n1 == 0);
            n2 = randUsual();

        } else {
            n1 = randUsual();
            n2 = randUsual();
        }
        return new EquationMathTask(n1, op, n2, xStart);
    }
}
