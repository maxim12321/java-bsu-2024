package by.KirillBukato.quizer.generators.math;

import by.KirillBukato.quizer.tasks.math.MathTask;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Random;

public abstract class AbstractMathTaskGenerator<T extends MathTask> implements MathTaskGenerator<T> {

    public AbstractMathTaskGenerator(int minNumber,
                                     int maxNumber,
                                     EnumSet<MathTask.Operation> enumSet) {
        this.minNumber = minNumber;
        this.maxNumber = maxNumber;
        operators = new ArrayList<>();
        operators.addAll(enumSet);
        RuntimeException e = validateGenerator();
        if (e != null) {
            throw e;
        }
    }

    @Override
    public RuntimeException validateGenerator() {
        if (getMinNumber() > getMaxNumber()) {
            return new IllegalArgumentException("Min value is greater than Max value");
        }
        return null;
    }

    @Override
    public int getMinNumber() {
        return minNumber;
    }

    @Override
    public int getMaxNumber() {
        return maxNumber;
    }

    @Override
    public T generate() {
        T task;
        do {
            task = generateUnvalidated();
        } while (!task.isValid());
        return task;
    }

    protected int getRandomNumber() {
        Random random = new Random();
        return random.nextInt(getDiffNumber() + 1) + getMinNumber();
    }

    protected MathTask.Operation getRandomOperation() {
        Random random = new Random();
        return operators.get(random.nextInt(operators.size()));
    }

    protected boolean operationsIsDivision() {
        return EnumSet.copyOf(operators).equals(EnumSet.of(
                MathTask.Operation.DIVIDE));
    }

    protected boolean operationsIsDivisionAndMultiplication() {
        EnumSet<MathTask.Operation> set = EnumSet.copyOf(operators);
        set.removeAll(EnumSet.of(MathTask.Operation.DIVIDE, MathTask.Operation.MULTIPLY));
        return set.isEmpty();
    }

    private final int minNumber;
    private final int maxNumber;
    private final ArrayList<MathTask.Operation> operators;
}
