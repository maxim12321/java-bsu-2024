package by.KirillBukato.quizer.generators.math;

import by.KirillBukato.quizer.tasks.math.MathTask;
import by.KirillBukato.quizer.tasks.math.SimpleExpressionTask;

import java.util.EnumSet;


public class SimpleExpressionTaskGenerator extends AbstractExpressionTaskGenerator<SimpleExpressionTask> {

    /**
     * @param minNumber    минимальное число
     * @param maxNumber    максимальное число
     * @param operationSet множество разрешённых операций
     */
    public SimpleExpressionTaskGenerator(int minNumber, int maxNumber, EnumSet<MathTask.Operation> operationSet) {
        super(minNumber, maxNumber, operationSet);
    }

    public SimpleExpressionTask generateUnvalidated() {
        return new SimpleExpressionTask(getRandomNumber(), getRandomOperation(), getRandomNumber());
    }
}
