package by.VeranikaFiliptsova.quizer.generators.math;

import by.VeranikaFiliptsova.quizer.tasks.math.ExpressionMathTask;
import by.VeranikaFiliptsova.quizer.tasks.math.MathTask.Operation;

import java.util.EnumSet;
import java.util.Random;

import static java.lang.Math.abs;

public class ExpressionMathTaskGenerator extends AbstractMathTaskGenerator<ExpressionMathTask>{
    /**
     * @param minNumber              минимальное число
     * @param maxNumber              максимальное число
     * @param operations            разрешить генерацию с операторами, перечисленными в {@link EnumSet}
     */


    public ExpressionMathTaskGenerator(
            int minNumber,
            int maxNumber,
            EnumSet<Operation> operations
    ) {
        super(minNumber, maxNumber, operations);
    }

    /**
     * return задание типа {@link ExpressionMathTask}
     */
    public ExpressionMathTask generate() {
        if (isNotValid() || (maxNumb == 0 && minNumb == 0 && operationAllowed.size() == 1 && operationAllowed.contains(Operation.DIV))) {
            throw new RuntimeException("impossible to generate valid task");
        }

        int n1;
        int n2;
        Random rand = new Random();
        Operation op = Operation.SUM;

        int opIndex = rand.nextInt(operationAllowed.size());
        int i = 0;
        for (Operation element : operationAllowed) {
            if (i == opIndex) {
                op = element;
            }
            i++;
        }

        if (op.equals(Operation.DIV)) {
            do {
                n2 = randUsual();
            } while (n2 == 0);
            if (n2 > 0) {
                n1 = n2 * rand.nextInt(maxNumb/n2 + 1);
            } else {
                n1 = n2 * rand.nextInt(minNumb/n2 + 1);
            }
        } else {
            n1 = randUsual();
            n2 = randUsual();
        }
        return new ExpressionMathTask(n1, op, n2);
    }
}
