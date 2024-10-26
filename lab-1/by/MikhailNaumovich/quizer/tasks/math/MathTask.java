package by.MikhailNaumovich.quizer.tasks.math;

import by.MikhailNaumovich.quizer.Task;

public interface MathTask extends Task {
    
    boolean isValid();

    double compute();
}