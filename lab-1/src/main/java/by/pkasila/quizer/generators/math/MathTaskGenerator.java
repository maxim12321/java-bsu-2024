package by.pkasila.quizer.generators.math;

import by.pkasila.quizer.generators.TaskGenerator;
import by.pkasila.quizer.common.MathTask;

public interface MathTaskGenerator<T extends MathTask> extends TaskGenerator<T> {

    int getMinNumber();

    int getMaxNumber();

    T generateUnvalidated();

    default int getDiffNumber() {
        return getMaxNumber() - getMinNumber();
    }
}