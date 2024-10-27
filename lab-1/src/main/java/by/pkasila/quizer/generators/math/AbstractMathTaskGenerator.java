package by.pkasila.quizer.generators.math;

import by.pkasila.quizer.common.MathOperation;
import by.pkasila.quizer.exceptions.BadGeneratorException;
import by.pkasila.quizer.common.MathTask;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Random;

public abstract class AbstractMathTaskGenerator<T extends MathTask> implements MathTaskGenerator<T> {

    private final int minNumber;

    private final int maxNumber;

    private final ArrayList<MathOperation> operationArray;

    /**
     * @param minNumber    минимальное число
     * @param maxNumber    максимальное число
     * @param operationArray множество разрешённых операций
     */
    public AbstractMathTaskGenerator(int minNumber,
                                     int maxNumber,
                                     EnumSet<MathOperation> operationArray) throws BadGeneratorException {
        this.minNumber = minNumber;
        this.maxNumber = maxNumber;
        this.operationArray = new ArrayList<>(operationArray);
        validateGenerator();
    }

    public void validateGenerator() throws BadGeneratorException {
        if (getMinNumber() > getMaxNumber()) {
            throw new BadGeneratorException("Min value is greater than Max value");
        }
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
        } while (task.isInvalid());
        return task;
    }

    protected int getRandomNumber() {
        Random random = new Random();
        return random.nextInt(getDiffNumber() + 1) + getMinNumber();
    }

    protected MathOperation getRandomOperation() {
        Random random = new Random();
        return operationArray.get(random.nextInt(operationArray.size()));
    }

    protected boolean hasDivision() {
        return EnumSet.copyOf(operationArray).equals(EnumSet.of(
                MathOperation.DIVISION));
    }

    protected boolean hasDivisionAndMultiplication() {
        EnumSet<MathOperation> set = EnumSet.copyOf(operationArray);
        set.removeAll(EnumSet.of(MathOperation.DIVISION, MathOperation.MULTIPLICATION));
        return set.isEmpty();
    }
}
