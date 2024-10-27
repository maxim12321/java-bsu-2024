package by.mmaxemm.quizer.generators.math;

import by.mmaxemm.quizer.tasks.math.ExpressionTask;
import by.mmaxemm.quizer.tasks.math.MathTask;
import java.util.EnumSet;

public class ExpressionTaskGenerator extends AbstractMathTaskGenerator {

    /**
     * @param minNumber              минимальное число
     * @param maxNumber              максимальное число
     * @param availableOperators     допустимые в генерации операторы
     */
    public ExpressionTaskGenerator(
            int minNumber,
            int maxNumber,
            EnumSet<MathTask.Operation> availableOperators
    ) {
        super(minNumber, maxNumber, availableOperators);
    }

    /**
     * return задание типа {@link ExpressionTask}
     */
    @Override
    public ExpressionTask generate() {
        int num1 = random.nextInt(maxNumber - minNumber + 1) + minNumber;
        int num2 = random.nextInt(maxNumber - minNumber + 1) + minNumber;
        int operatorIndex = random.nextInt(availableOperators.size());
        MathTask.Operation operator = availableOperators.get(operatorIndex);
        return new ExpressionTask(num1, num2, operator);
    }
}