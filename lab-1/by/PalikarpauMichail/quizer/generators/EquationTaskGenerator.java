package by.PalikarpauMichail.quizer.generators;
import by.PalikarpauMichail.quizer.generators.math.AbstractMathTaskGenerator;
import by.PalikarpauMichail.quizer.tasks.EquationTask;
import by.PalikarpauMichail.quizer.tasks.math.MathTask;
import by.PalikarpauMichail.quizer.tasks.math.MathTask.Operation;

import java.util.AbstractMap.SimpleEntry;
import java.util.EnumSet;
import java.util.List;

public class EquationTaskGenerator extends AbstractMathTaskGenerator {
    /**
     * @param minNumber              минимальное число
     * @param maxNumber              максимальное число
     * @param generateSum            разрешить генерацию с оператором +
     * @param generateDifference     разрешить генерацию с оператором -
     * @param generateMultiplication разрешить генерацию с оператором *
     * @param generateDivision       разрешить генерацию с оператором /
     */
    public EquationTaskGenerator(
        int minNumber,
        int maxNumber,
        EnumSet<MathTask.Operation> operations
    ) {
        super(minNumber, maxNumber, operations);
    }
    
    /**
     * return задание типа {@link ExpressionTask}
     */
    public EquationTask generate() {
        SimpleEntry<List<Integer>, Operation> entry = generateArguments();
        var arguments = entry.getKey();
        var operation = entry.getValue();
        return new EquationTask(arguments.get(0), arguments.get(1), arguments.get(2), operation);
    }
}