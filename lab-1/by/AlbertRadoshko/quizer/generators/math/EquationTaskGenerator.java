package by.AlbertRadoshko.quizer.generators.math;

import by.AlbertRadoshko.quizer.TaskGenerator;
import by.AlbertRadoshko.quizer.tasks.math.EquationTask;
import by.AlbertRadoshko.quizer.tasks.math.MathTask;

import java.util.EnumSet;
import java.util.Random;

public class EquationTaskGenerator extends AbstractMathTaskGenerator<EquationTask> {
    /**
     * @param minNumber минимальное число
     * @param maxNumber максимальное число
     * @param allowed   сет допустимых операций для генерации
     */
    public EquationTaskGenerator(
            int minNumber,
            int maxNumber,
            EnumSet<MathTask.Operation> allowed
    ) {
        super(minNumber, maxNumber, allowed);
    }

    /**
     * return задание типа {@link EquationTask}
     */
    public EquationTask generate() {
        var rand = new Random();
        EquationTask.EquationType type = EquationTask.EquationType.LHS;
        if (rand.nextInt(2) == 1) {
            type = EquationTask.EquationType.RHS;
        }
        var op = allowed.stream().toList().get(rand.nextInt(allowed.size()));
        return switch (op) {
            case ADDITION, SUBTRACTION -> new EquationTask(rand.nextInt(minNumber, maxNumber + 1),
                    rand.nextInt(minNumber, maxNumber + 1), op, type);
            case MULTIPLICATION -> {
                int lhs = genNonZero();
                int rhs = genMultiple(lhs);
                yield new EquationTask(lhs, rhs, op, type);
            }
            case DIVISION -> {
                int lhs, rhs;
                if (type == EquationTask.EquationType.LHS) {
                    // x / lhs = rhs
                    lhs = genNonZero();
                    int x = genMultiple(lhs);
                    rhs = x / lhs;
                } else {
                    // lhs / x = rhs
                    int x = genNonZero();
                    lhs = genMultiple(x);
                    rhs = lhs / x;
                }
                yield new EquationTask(lhs, rhs, op, type);
            }
        };
    }
}
