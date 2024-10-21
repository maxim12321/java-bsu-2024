package by.VeranikaFiliptsova.quizer.generators.math;

//import by.VeranikaFiliptsova.quizer.Operation;
import by.VeranikaFiliptsova.quizer.Task;
import by.VeranikaFiliptsova.quizer.TaskGenerator;
import by.VeranikaFiliptsova.quizer.tasks.math.ExpressionMathTask;
import by.VeranikaFiliptsova.quizer.tasks.math.MathTask.Operation;

import by.VeranikaFiliptsova.quizer.tasks.math.MathTask;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class ExpressionMathTaskGenerator extends AbstractMathTaskGenerator{
    /**
     * @param minNumber              минимальное число
     * @param maxNumber              максимальное число
     * @param generateSum            разрешить генерацию с оператором +
     * @param generateDifference     разрешить генерацию с оператором -
     * @param generateMultiplication разрешить генерацию с оператором *
     * @param generateDivision       разрешить генерацию с оператором /
     */

    int minNumb;
    int maxNumb;
    Set<Operation> operationAllowed = new HashSet<>();

    public ExpressionMathTaskGenerator(
            int minNumber,
            int maxNumber,
            boolean generateSum,
            boolean generateDifference,
            boolean generateMultiplication,
            boolean generateDivision
    ) {
        minNumb = minNumber;
        maxNumb = maxNumber;
        if (!generateDifference && !generateSum && !generateDivision && !generateMultiplication) {
            throw new RuntimeException("none of operations are allowed"); //TODO свое исключение
        }
        if (generateSum) {
            operationAllowed.add(Operation.SUM);
        }
        if (generateDifference) {
            operationAllowed.add(Operation.DIFF);
        }
        if (generateMultiplication) {
            operationAllowed.add(Operation.MUL);
        }
        if (generateDivision) {
            operationAllowed.add(Operation.DIV);
        }
    }

    /**
     * return задание типа {@link ExpressionMathTask}
     */
    public ExpressionMathTask generate() {
        Random rand = new Random();
        int n1;
        int n2;
        Operation op = Operation.SUM;
        int opIndex = rand.nextInt(operationAllowed.size());
        int i = 0;
        for (Operation element : operationAllowed) {
            if (i == opIndex) {
                op = element;
            }
            i++;
        }
        do {
            n1 = minNumb + rand.nextInt(maxNumb - minNumb + 1);
            n2 = minNumb + rand.nextInt(maxNumb - minNumb + 1);
        } while (n2 == 0 && op.equals(Operation.DIV));
        return new ExpressionMathTask(n1, op, n2);
    }
}
