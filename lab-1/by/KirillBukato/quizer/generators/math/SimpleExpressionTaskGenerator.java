package by.KirillBukato.quizer.generators.math;

import by.KirillBukato.quizer.exceptions.InvalidGeneratorException;
import by.KirillBukato.quizer.tasks.math.MathOperation;
import by.KirillBukato.quizer.tasks.math.SimpleExpressionTask;

import java.util.EnumSet;

public class SimpleExpressionTaskGenerator extends AbstractExpressionTaskGenerator<SimpleExpressionTask> {

    /**
     * @param minNumber    минимальное число
     * @param maxNumber    максимальное число
     * @param operationSet множество разрешённых операций
     */
    public SimpleExpressionTaskGenerator(int minNumber, int maxNumber, EnumSet<MathOperation> operationSet) throws InvalidGeneratorException {
        super(minNumber, maxNumber, operationSet);
    }

    public SimpleExpressionTask generateUnvalidated() {
        return new SimpleExpressionTask(getRandomNumber(), getRandomOperation(), getRandomNumber());
    }
}
