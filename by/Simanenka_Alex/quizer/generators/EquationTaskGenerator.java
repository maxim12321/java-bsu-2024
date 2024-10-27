package by.Simanenka_Alex.quizer.generators;

import by.Simanenka_Alex.quizer.Task;
import by.Simanenka_Alex.quizer.tasks.EquationTask;
import by.Simanenka_Alex.quizer.tasks.ExpressionTask;
import by.Simanenka_Alex.quizer.tasks.MathTask;

import java.util.EnumSet;
import java.util.Random;

public class EquationTaskGenerator extends AbstractMathTaskGenerator{
    public EquationTaskGenerator(int minNumber, int maxNumber, EnumSet<MathTask.Operation> generateOperation) {
        super(minNumber, maxNumber, generateOperation);
    }

    /**
     * return задание типа {@link EquationTask}
     */
    public EquationTask generate() {
        EquationTask task = new EquationTask(generateArg(), generateOp(), generateArg(), generateArgIsRight());
        while (!isValid(task)) {
            task = new EquationTask(generateArg(), generateOp(), generateArg(), generateArgIsRight());
        }
        return task;
    }

    static boolean isValid(EquationTask task) {
        return !(
                ((task.getOp() == MathTask.Operation.DIV) &&
                        (task.getRightArg() == 0) && (!task.isArgIsRigth()))
                || ((task.getOp() == MathTask.Operation.MUL) &&
                        (task.getLeftArg() == 0))
                || ((task.getOp() == MathTask.Operation.DIV) &&
                        (task.getLeftArg() == 0) && (task.isArgIsRigth()))
                || ((task.getOp() == MathTask.Operation.DIV) &&
                        (task.getRightArg() != 0) && (task.getLeftArg() == 0) && (!task.isArgIsRigth()))
        );
    }

    public boolean generateArgIsRight() {
        Random rand = new Random();
        return rand.nextInt(2) == 0;
    }

}
