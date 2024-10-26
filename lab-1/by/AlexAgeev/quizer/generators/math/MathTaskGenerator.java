package by.AlexAgeev.quizer.generators.math;

import by.AlexAgeev.quizer.TaskGenerator;
import by.AlexAgeev.quizer.tasks.math.MathTask;

public interface MathTaskGenerator extends TaskGenerator<MathTask> {

    int getMinNumber();
    int getMaxNumber();

    default int getDifNumber() {
        return getMaxNumber() - getMinNumber();
    }
}
