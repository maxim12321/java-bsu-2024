package by.KirillBukato.quizer.generators.math;

import by.KirillBukato.quizer.tasks.math.ExpressionTask;
import by.KirillBukato.quizer.tasks.math.MathTask;

import java.util.EnumSet;


public class ExpressionTaskGenerator extends AbstractExpressionTaskGenerator<ExpressionTask> {

    /**
     * @param minNumber минимальное число
     * @param maxNumber максимальное число
     * @param enumSet   множество разрешённых операций
     */
    public ExpressionTaskGenerator(int minNumber, int maxNumber, EnumSet<MathTask.Operation> enumSet) {
        super(minNumber, maxNumber, enumSet);
    }

    public ExpressionTask generateUnvalidated() {
        return new ExpressionTask(getRandomNumber(), getRandomOperation(), getRandomNumber());
    }
}
