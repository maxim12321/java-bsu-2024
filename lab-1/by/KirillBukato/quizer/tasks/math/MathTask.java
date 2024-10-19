package by.KirillBukato.quizer.tasks.math;

import by.KirillBukato.quizer.Task;

public interface MathTask extends Task {

    boolean isValid();

    double computeAnswer();
}
