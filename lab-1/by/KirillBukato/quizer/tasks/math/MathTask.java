package by.KirillBukato.quizer.tasks.math;

import by.KirillBukato.quizer.tasks.Task;

public interface MathTask extends Task {

    boolean isValid();

    double computeAnswer();
}
