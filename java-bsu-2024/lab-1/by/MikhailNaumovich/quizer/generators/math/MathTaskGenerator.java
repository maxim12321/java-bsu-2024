package by.MikhailNaumovich.quizer.generators.math;

import by.MikhailNaumovich.quizer.exceptions.InvalidArgumentException;

import by.MikhailNaumovich.quizer.TaskGenerator;
import by.MikhailNaumovich.quizer.tasks.math.MathTask;


public interface MathTaskGenerator<T extends MathTask> extends TaskGenerator<T>{

    int getMinNumber();

    int getMaxNumber();

    T preValidationGenerator();
    
    default int getDiffNumber() throws InvalidArgumentException{
        return getMaxNumber() - getMinNumber();
    }
    
}