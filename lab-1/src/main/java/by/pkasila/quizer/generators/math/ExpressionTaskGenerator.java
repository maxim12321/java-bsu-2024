package by.pkasila.quizer.generators.math;

import by.pkasila.quizer.common.MathOperation;
import by.pkasila.quizer.exceptions.BadGeneratorException;
import by.pkasila.quizer.tasks.math.ExpressionTask;

import java.util.EnumSet;

public class ExpressionTaskGenerator extends AbstractExpressionTaskGenerator<ExpressionTask> {

    /**
     * @param minNumber    минимальное число
     * @param maxNumber    максимальное число
     * @param operationSet множество разрешённых операций
     */
    public ExpressionTaskGenerator(int minNumber, int maxNumber, EnumSet<MathOperation> operationSet) throws BadGeneratorException {
        super(minNumber, maxNumber, operationSet);
    }

    public ExpressionTask generateUnvalidated() {
        return new ExpressionTask(getRandomNumber(), getRandomOperation(), getRandomNumber());
    }
}
