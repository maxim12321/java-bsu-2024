package by.volkovets.quizer.generators.math;

import java.util.EnumSet;

import by.volkovets.quizer.tasks.math.MathTask;

public abstract class AbsrtactMathTaskGenerator<T extends MathTask> implements MathTaskGenerator<T> {

    protected int min;
    protected int max;
    protected EnumSet<MathTask.Operation> available_opearions;

    public AbsrtactMathTaskGenerator(int Min, int Max, EnumSet<MathTask.Operation> available_opearions) {
        this.min = Min;
        this.max = Max;
        this.available_opearions = available_opearions;
    }

    
    public int getMinNumber() {
        return this.min;
    }

    
    public int getMaxNumber() {
        return this.max;
    }
}
