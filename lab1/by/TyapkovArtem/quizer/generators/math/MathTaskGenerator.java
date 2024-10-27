package lab1.by.TyapkovArtem.quizer.generators.math;

import lab1.by.TyapkovArtem.quizer.TaskGenerator;

public interface MathTaskGenerator extends TaskGenerator {
    int GetMaximum();
    int GetMinimum();
    default int GetDiff() {
        return  GetMaximum() - GetMinimum();
    }
}
