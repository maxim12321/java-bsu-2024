package by.VeranikaFiliptsova.quizer.generators.math;

import by.VeranikaFiliptsova.quizer.exceptions.GeneratorNotValidException;
import by.VeranikaFiliptsova.quizer.tasks.math.ExpressionMathTask;
import by.VeranikaFiliptsova.quizer.tasks.math.MathTask.Operation;
import by.VeranikaFiliptsova.quizer.tasks.math.TextExpressionMathTask;

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
        if (isNotValid()) {
            throw new GeneratorNotValidException("Set of allowed operations is empty or minimum value is greater than maximum value");
        }
        if (maxNumb == 0 && minNumb == 0 && operationAllowed.size() == 1 && operationAllowed.contains(Operation.DIV)) {
            throw new GeneratorNotValidException("impossible to generate task without division on 0");
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
//                В некоторых случаях такая генерация может привести к незначительному выходу за границы из-за округления вниз,
//                        но главное, что она точно правильно работает, когда maxNumber>0 и minNumber>0
//                (то есть выдает корректные текстовые задачи)
                n1 = n2 * ((minNumb + n2 - 1)/n2 + rand.nextInt((maxNumb - minNumb)/n2 + 1));
            } else {
                n1 = n2 * ((maxNumb + n2 - 1)/n2 + rand.nextInt((minNumb - maxNumb)/n2 + 1));
            }
        } else {
            n1 = randUsual();
            n2 = randUsual();
        }
        return new ExpressionMathTask(n1, op, n2);
    }
}
