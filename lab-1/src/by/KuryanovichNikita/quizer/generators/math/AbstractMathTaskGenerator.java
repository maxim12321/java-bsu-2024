package by.KuryanovichNikita.quizer.generators.math;

import by.KuryanovichNikita.quizer.exceptions.NotValidGenerator;
import by.KuryanovichNikita.quizer.tasks.Task;
import by.KuryanovichNikita.quizer.tasks.math.MathTask;

import java.util.EnumSet;
import java.util.Random;

public abstract class AbstractMathTaskGenerator implements MathTaskGenerator {

    protected int minNumber;
    protected int maxNumber;
    protected final EnumSet<MathTask.Operation> operations;

    protected AbstractMathTaskGenerator(int minNumber, int maxNumber,
                                     EnumSet<MathTask.Operation> operations) {
        this.minNumber = minNumber;
        this.maxNumber = maxNumber;
        this.operations = operations;
    }


    @Override
    public int getMinNumber() {
        return minNumber;
    }

    @Override
    public int getMaxNumber() {
        return maxNumber;
    }

    public int getRandomNumber(Random rand){
        return rand.nextInt(maxNumber - minNumber) + minNumber;
    }
}
