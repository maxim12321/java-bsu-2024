package by.volkovets.quizer.generators.math;

import by.volkovets.quizer.exceptions.MinIsBiggerThanMaxException;
import by.volkovets.quizer.tasks.math.EquationTask;
import by.volkovets.quizer.tasks.math.MathTask;
import java.util.Random;
import java.util.EnumSet;

public class EquationTaskGenerator extends AbsrtactMathTaskGenerator<EquationTask> {
    public EquationTaskGenerator(int Min, int Max, EnumSet<MathTask.Operation> available_opearions) {
        super(Min, Max, available_opearions);
    }

    @Override
    public EquationTask generate() throws MinIsBiggerThanMaxException {
        if (this.min > this.max) {
            throw new MinIsBiggerThanMaxException();
        }
        Random random = new Random();
        int first = random.nextInt(this.max - this.min + 1) + this.min;
        int second = random.nextInt(this.max - this.min + 1) + this.min;

        
        int oper_index = random.nextInt(available_opearions.size());
        MathTask.Operation operation = (MathTask.Operation) available_opearions.toArray()[oper_index];

        int X_Pos = random.nextInt(2);

        EquationTask task = new EquationTask(X_Pos, operation, first, second);

        return task;
    }
}
