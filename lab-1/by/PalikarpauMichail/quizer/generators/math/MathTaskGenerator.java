package by.PalikarpauMichail.quizer.generators.math;
import by.PalikarpauMichail.quizer.TaskGenerator;
import by.PalikarpauMichail.quizer.tasks.math.MathTask;


public interface MathTaskGenerator extends TaskGenerator {
    int getMinNumber();
    int getMaxNumber();
    int getRandomNumber();
    MathTask.Operation getRandomOperation();
    MathTask generate();
    
    default int getDiffNumber() {
        return getMaxNumber() - getMinNumber();
    }
}
