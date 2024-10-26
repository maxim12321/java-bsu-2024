package by.chewy_pegasus.quizer.generators;

import by.chewy_pegasus.quizer.Task;
import by.chewy_pegasus.quizer.generators.math.AbstractMathTaskGenerator;
import by.chewy_pegasus.quizer.tasks.ExpressionTask;
import by.chewy_pegasus.quizer.tasks.math.MathTask;

import java.util.EnumSet;
import java.util.concurrent.ThreadLocalRandom;

public class ExpressionTaskGenerator extends AbstractMathTaskGenerator<ExpressionTask> {
    /**
     * @param minNumber              минимальное число
     * @param maxNumber              максимальное число
     * @param operationEnumSet       доступные операции
     */
    public ExpressionTaskGenerator(
            int minNumber,
            int maxNumber,
            EnumSet<MathTask.Operation> operationEnumSet
    ) {
        super(minNumber, maxNumber);
        validParameters(minNumber, maxNumber, operationEnumSet);

        if (operationEnumSet.contains(MathTask.Operation.SUM)) {
            a.add('+');
        }
        if (operationEnumSet.contains(MathTask.Operation.DIV)) {
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
     * return задание типа {@link ExpressionTask}
     */
    public ExpressionTask generate() {
        int leftNum = ThreadLocalRandom.current().nextInt(this.minNumber, this.maxNumber + 1);
        int rightNum = ThreadLocalRandom.current().nextInt(this.minNumber, this.maxNumber + 1);
        char op = a.get(ThreadLocalRandom.current().nextInt(0, a.size()));

        if (op == '/') {
            while (rightNum == 0) {
                rightNum = ThreadLocalRandom.current().nextInt(this.minNumber, this.maxNumber + 1);
            }
            while (leftNum % rightNum != 0) {
                leftNum = ThreadLocalRandom.current().nextInt(this.minNumber, this.maxNumber + 1);
            }
        }
        return new ExpressionTask(leftNum, rightNum, op);
    }
}
