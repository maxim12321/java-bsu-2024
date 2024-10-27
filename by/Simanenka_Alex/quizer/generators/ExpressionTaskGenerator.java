package by.Simanenka_Alex.quizer.generators;

import by.Simanenka_Alex.quizer.TaskGenerator;
import by.Simanenka_Alex.quizer.tasks.AbstractMathTask;
import by.Simanenka_Alex.quizer.tasks.ExpressionTask;
import by.Simanenka_Alex.quizer.tasks.MathTask;

import java.util.EnumSet;

public class ExpressionTaskGenerator extends AbstractMathTaskGenerator {
    /**
     * @param minNumber              минимальное число
     * @param maxNumber              максимальное число
     * @param generateOperation      разрешённые операции
     */
    public ExpressionTaskGenerator(
            int minNumber,
            int maxNumber,
            EnumSet<MathTask.Operation> generateOperation
    ) {
        super(minNumber, maxNumber, generateOperation);
    }

    /**
     * return задание типа {@link ExpressionTask}
     */
    public ExpressionTask generate() {
        ExpressionTask task = new ExpressionTask(generateArg(), generateOp(), generateArg());
        while (!isValid(task)) {
            task = new ExpressionTask(generateArg(), generateOp(), generateArg());
        }
        return task;
    }

    static boolean isValid(ExpressionTask task) {
        return !((task.getOp() == MathTask.Operation.DIV) && (task.getRightArg() == 0));
    }

}