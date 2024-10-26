package by.chewy_pegasus.quizer.generators.math;

import by.chewy_pegasus.quizer.TaskGenerator;
import by.chewy_pegasus.quizer.tasks.math.MathTask;

import java.util.EnumSet;

public interface MathTaskGenerator<T extends MathTask> extends TaskGenerator<MathTask> {
    void validParameters(int min, int max, EnumSet<MathTask.Operation> operationEnumSet) throws RuntimeException;
    int getMinNumber();
    int getMaxNumber();
    default int getDiffNumber() {
        return getMaxNumber() - getMinNumber();
    }
}
