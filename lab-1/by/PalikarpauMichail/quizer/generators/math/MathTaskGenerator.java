package by.PalikarpauMichail.quizer.generators.math;

import by.PalikarpauMichail.quizer.TaskGenerator;
import by.PalikarpauMichail.quizer.tasks.math.MathTask;


public interface MathTaskGenerator<T extends MathTask> extends TaskGenerator<T> {
    int getMinNumber();
    int getMaxNumber();
    int getRandomNumber();
    MathTask.Operation getRandomOperation();
    T generate();
    
    default int getDiffNumber() {
        return getMaxNumber() - getMinNumber();
    }
}
