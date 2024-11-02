package by.SanchukS.quizer.generators;

import by.SanchukS.quizer.Operation;
import by.SanchukS.quizer.generators.math.AbstractMathTaskGenerator;
import by.SanchukS.quizer.tasks.ExpressionTask;
import by.SanchukS.quizer.tasks.math.MathTask;

import java.util.EnumSet;

public class ExpressionTaskGenerator extends AbstractMathTaskGenerator {
    /**
     * @param minNumber              минимальное число
     * @param maxNumber              максимальное число
     * @param operations             набор разрешённых операций
     */
    public ExpressionTaskGenerator(
            int minNumber,
            int maxNumber,
            EnumSet<Operation> operations
    ) {
        super(
                minNumber,
                maxNumber,
                operations
        );
    }

    /**
     * return задание типа {@link ExpressionTask}
     */
    @Override
    public MathTask generate() {
        return new ExpressionTask(generateExpression());
    }
}