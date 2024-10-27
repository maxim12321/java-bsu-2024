package by.MikhailNaumovich.quizer.generators.math;

import by.MikhailNaumovich.quizer.tasks.math.AbstractMathTask;
import by.MikhailNaumovich.quizer.tasks.math.MathEnum;

import by.MikhailNaumovich.quizer.exceptions.InvalidGeneratorException;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.LinkedList;
import java.util.List;


public abstract class AbstractMathTaskGenerator<T extends AbstractMathTask> implements MathTaskGenerator<T> {

    public AbstractMathTaskGenerator(int minNumber, int maxNumber, EnumSet<MathEnum> operations) throws InvalidGeneratorException {
        this.minNumber = minNumber;
        this.maxNumber = maxNumber;
        this.operations = new ArrayList<>(operations);
        preValidationGenerator();
    }

    public void validateGenerator() throws InvalidGeneratorException {
        if (minNumber > maxNumber) {
            throw new InvalidGeneratorException("Min number is greater than max number");
        }
        if (operations.isEmpty()) {
            throw new InvalidGeneratorException("No operations selected");
        }
    }

    public int getMinNumber() {
        return minNumber;
    }

    public int getMaxNumber() {
        return maxNumber;
    }

    protected boolean hasOnlyDivision() {
        return operations.stream()
                .allMatch(op -> op == MathEnum.DIVIDE) && !operations.isEmpty();
    }

    protected boolean hasOnlyDivisionOrMultiplication() {
        return operations.stream()
                .allMatch(op -> op == MathEnum.DIVIDE || op == MathEnum.MULTIPLY) 
                && !operations.isEmpty();
    }

    @Override
    public T generate() {
        T task;
        do {
            task = preValidationGenerator();
        } while (!task.isValid());
        return task;
    }

    protected int generateRandomNumber() {
        return (int) (Math.random() * (getDiffNumber() + 1) + minNumber);
    }

    protected MathEnum generateRandomOperation() {
        List<MathEnum> operationsList = new LinkedList<>(operations);
        return operationsList.get((int) (Math.random() * operationsList.size()));
    }

    protected final int minNumber;
    protected final int maxNumber;
    private final ArrayList<MathEnum> operations;
}
