package by.VeranikaFiliptsova.quizer.generators.math;

import by.VeranikaFiliptsova.quizer.exceptions.GeneratorNotValidException;
import by.VeranikaFiliptsova.quizer.tasks.math.ExpressionMathTask;
import by.VeranikaFiliptsova.quizer.tasks.math.MathTask;
import by.VeranikaFiliptsova.quizer.tasks.math.TextExpressionMathTask;

import java.util.EnumSet;

public class TextExpressionMathTaskGenerator extends ExpressionMathTaskGenerator{
    /**
     * @param minNumber  минимальное число
     * @param maxNumber  максимальное число
     * @param operations разрешить генерацию с операторами, перечисленными в {@link EnumSet}
     */
    public TextExpressionMathTaskGenerator(int minNumber, int maxNumber, EnumSet<MathTask.Operation> operations) {
        super(minNumber, maxNumber, operations);
    }

    public TextExpressionMathTask generate() {
        if (minNumb <= 0) {
            throw new GeneratorNotValidException("minimum allowed number should be positive for textExpression tasks");
        }
        ExpressionMathTask task = super.generate();
        return new TextExpressionMathTask(task);
    }
}
