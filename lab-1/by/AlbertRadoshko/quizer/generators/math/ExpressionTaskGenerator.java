package by.AlbertRadoshko.quizer.generators.math;

import by.AlbertRadoshko.quizer.TaskGenerator;
import by.AlbertRadoshko.quizer.tasks.math.ExpressionTask;
import by.AlbertRadoshko.quizer.tasks.math.MathTask;

import java.util.EnumSet;
import java.util.Random;

public class ExpressionTaskGenerator extends AbstractMathTaskGenerator<ExpressionTask> {
    /**
     * @param minNumber              минимальное число
     * @param maxNumber              максимальное число
     * @param allowed                сет допустимых операций для генерации
     */
    public ExpressionTaskGenerator(
            int minNumber,
            int maxNumber,
            EnumSet<MathTask.Operation> allowed
    ) {
        super(minNumber, maxNumber, allowed);
    }

    /**
     * return задание типа {@link ExpressionTask}
     */
    public ExpressionTask generate() {
        Random rand = new Random();
        var op = allowed.stream().toList().get(rand.nextInt(allowed.size()));
        if (op == MathTask.Operation.DIVISION) {
            var rhs = genNonZero();
            var lhs = genMultiple(rhs);
            return new ExpressionTask(lhs, rhs, op);
        }
        return new ExpressionTask(rand.nextInt(minNumber, maxNumber + 1),
                rand.nextInt(minNumber, maxNumber + 1),
                op);
    }
}