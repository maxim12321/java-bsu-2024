package by.Alesia.quizer.generators.math;

import by.Alesia.quizer.tasks.ExpressionTask;
import by.Alesia.quizer.tasks.math.MathTask;

import java.util.EnumSet;
import java.util.Random;
import java.util.Vector;

import static by.Alesia.quizer.tasks.math.MathTask.Operation.*;
import static by.Alesia.quizer.tasks.math.MathTask.Operation.DIV;

abstract public class AbstractMathTaskGenerator<T extends MathTask> implements MathTaskGenerator<T> {

    protected final int minNum;
    protected final int maxNum;
    protected final Random random = new Random();
    protected final EnumSet<MathTask.Operation> operations;

    protected AbstractMathTaskGenerator(int minNum, int maxNum, EnumSet<MathTask.Operation> operations) {
        this.operations = operations;
        this.minNum = minNum;
        this.maxNum = maxNum;
    }

    protected AbstractMathTaskGenerator(int minNumber, int maxNumber) {
        this(minNumber, maxNumber, EnumSet.allOf(MathTask.Operation.class));
    }

    public Integer genNum() {
        return random.nextInt(minNum, maxNum + 1);
    }

    public MathTask.Operation genOperation() {
        return operations.stream()
                .toList()
                .get(random.nextInt(operations.size()));
    }

    @Override
    public int minNumber() {
        return minNum;
    }

    @Override
    public int maxNumber() {
        return maxNum;
    }

}