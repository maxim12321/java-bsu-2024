package by.PalikarpauMichail.quizer.generators;
import by.PalikarpauMichail.quizer.exceptions.BadMathGeneratorBoundariesException;
import by.PalikarpauMichail.quizer.generators.math.AbstractMathTaskGenerator;
import by.PalikarpauMichail.quizer.tasks.ExpressionTask;
import by.PalikarpauMichail.quizer.tasks.math.MathTask;
import by.PalikarpauMichail.quizer.tasks.math.MathTask.Operation;

import java.util.AbstractMap.SimpleEntry;
import java.util.EnumSet;
import java.util.List;

public class ExpressionTaskGenerator extends AbstractMathTaskGenerator<ExpressionTask> {
    /**
     * @param minNumber              минимальное число
     * @param maxNumber              максимальное число
     * @param operations             список допустимых операций
     */
    public ExpressionTaskGenerator(
        int minNumber,
        int maxNumber,
        EnumSet<MathTask.Operation> operations
    ) throws BadMathGeneratorBoundariesException, IllegalArgumentException {
        super(minNumber, maxNumber, operations);
    }
    
    /**
     * return задание типа {@link ExpressionTask}
     */
    @Override
    public ExpressionTask generate() {
        SimpleEntry<Operation, List<Integer>> entry = generateArguments();
        var operation = entry.getKey();
        var arguments = entry.getValue();
        return new ExpressionTask(arguments.get(0), arguments.get(1), arguments.get(2), operation);
    }
}