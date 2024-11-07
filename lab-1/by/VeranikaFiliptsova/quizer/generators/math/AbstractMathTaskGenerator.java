package by.VeranikaFiliptsova.quizer.generators.math;

import by.VeranikaFiliptsova.quizer.Task;
import by.VeranikaFiliptsova.quizer.tasks.math.MathTask;

import java.util.EnumSet;
import java.util.Random;

import static java.lang.Math.abs;

abstract public class AbstractMathTaskGenerator<T extends MathTask> implements MathTaskGenerator<T>{
    int minNumb;
    int maxNumb;
    EnumSet<MathTask.Operation> operationAllowed = EnumSet.noneOf(MathTask.Operation.class);
    Random rand = new Random();

    public AbstractMathTaskGenerator(
            int minNumber,
            int maxNumber,
            EnumSet<MathTask.Operation> operations
    ) {
        minNumb = minNumber;
        maxNumb = maxNumber;
        operationAllowed.addAll(operations);
    }

    @Override
    public int getMinNumber() {
        return minNumb;
    }

    @Override
    public int getMaxNumber() {
        return maxNumb;
    }

    @Override
    abstract public T generate() ;

    public int getDiffNumber() {
        return maxNumb - minNumb;
    }

    @Override
    public boolean isNotValid() {
        return operationAllowed.isEmpty() || getDiffNumber() < 0;
    }


    public int randUsual() {
        return minNumb + rand.nextInt(maxNumb - minNumb + 1);
    }

}
