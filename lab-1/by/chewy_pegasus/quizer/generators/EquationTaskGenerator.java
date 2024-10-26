package by.chewy_pegasus.quizer.generators;

import by.chewy_pegasus.quizer.Task;
import by.chewy_pegasus.quizer.generators.math.AbstractMathTaskGenerator;
import by.chewy_pegasus.quizer.tasks.EquationTask;
import by.chewy_pegasus.quizer.tasks.math.MathTask;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.concurrent.ThreadLocalRandom;

public class EquationTaskGenerator extends AbstractMathTaskGenerator<EquationTask> {
    /**
     * @param minNumber              минимальное число
     * @param maxNumber              максимальное число
     */

    public EquationTaskGenerator(
            int minNumber,
            int maxNumber,
//            boolean generateSum,
//            boolean generateDifference,
//            boolean generateMultiplication,
//            boolean generateDivision
            EnumSet<MathTask.Operation> operationEnumSet
    ) {
        super(minNumber, maxNumber);
        validParameters(minNumber, maxNumber, operationEnumSet);

        if (operationEnumSet.contains(MathTask.Operation.SUM)) {
            a.add('+');
        }
        if (operationEnumSet.contains(MathTask.Operation.DIFF)) {
            a.add('-');
        }
        if (operationEnumSet.contains(MathTask.Operation.MULT)) {
            a.add('*');
        }
        if (operationEnumSet.contains(MathTask.Operation.DIV)) {
            a.add('/');
        }
    }

    /**
     * return задание типа {@link EquationTask}
     */
    public EquationTask generate() {
        final char op = a.get(ThreadLocalRandom.current().nextInt(0, a.size()));
        final boolean first = ThreadLocalRandom.current().nextInt(0, 3) == 0;
        int left = ThreadLocalRandom.current().nextInt(this.minNumber, this.maxNumber + 1);
        int right = ThreadLocalRandom.current().nextInt(this.minNumber, this.maxNumber + 1);
        int eq = 0;
        switch (op) {
            case '+' :
                eq = left + right;
                break;
            case '-' :
                eq = left - right;
                break;
            case '*' :
                if (right == 0 || left == 0) {
                    while (right == 0) {
                        right = ThreadLocalRandom.current().nextInt(this.minNumber, this.maxNumber + 1);
                    }
                    while (left == 0) {
                        left = ThreadLocalRandom.current().nextInt(this.minNumber, this.maxNumber + 1);
                    }
                }
                eq = left * right;
                break;
            case '/' :
                while (right == 0) {
                    right = ThreadLocalRandom.current().nextInt(this.minNumber, this.maxNumber + 1);
                }
                while (left % right != 0) {
                    left = ThreadLocalRandom.current().nextInt(this.minNumber, this.maxNumber + 1);
                }
                eq = left / right;
                break;
        }
        if (first) {
            return new EquationTask(true, right, eq, op);
        } else {
            return new EquationTask(false, left, eq, op);
        }
    }
}
