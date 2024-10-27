package by.TimurTahil.quizer.generators.math;

import by.TimurTahil.quizer.TaskGenerator;

public interface MathTaskGenerator extends TaskGenerator {

    int getMinNumber();

    int getMaxNumber();

    default int getDifNumber() {
        return getMaxNumber() - getMinNumber();
    }
}
