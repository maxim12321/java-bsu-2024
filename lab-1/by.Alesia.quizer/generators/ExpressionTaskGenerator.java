package by.Alesia.quizer.generators;

import by.Alesia.quizer.generators.math.AbstractMathTaskGenerator;
import by.Alesia.quizer.tasks.ExpressionTask;
import by.Alesia.quizer.tasks.math.MathTask;

import java.util.EnumSet;


public class ExpressionTaskGenerator extends AbstractMathTaskGenerator<ExpressionTask> {

    /**
     * @param minNumber              минимальное число
     * @param maxNumber              максимальное число
     * @param generateSum            разрешить генерацию с оператором +
     * @param generateDifference     разрешить генерацию с оператором -
     * @param generateMultiplication разрешить генерацию с оператором *
     * @param generateDivision       разрешить генерацию с оператором /
     */
    public ExpressionTaskGenerator(
            int minNumber,
            int maxNumber,
            EnumSet<MathTask.Operation> operations
    ) {
        super(minNumber, maxNumber, operations);
    }

    /**
     * return задание типа {@link ExpressionTask}
     */

    public ExpressionTask generate() {
        MathTask.Operation operation = genOperation();
        int a = genNum();
        int b = genNum();
        if (operation == MathTask.Operation.DIV) {
            while (b == 0 || ((a / b) * b != a)) {
                b = genNum();
            }
        }
        return new ExpressionTask(a, b, operation);
    }
}