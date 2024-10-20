package by.VadzimKamianetski.Quizer.TaskGenerators.Math;

import java.util.EnumSet;
import java.util.Random;

import by.VadzimKamianetski.Quizer.Tasks.Math.MathTask;

public abstract class AbstractMathTaskGenerator<T extends MathTask> implements MathTaskGenerator<T> {

    protected int minNumber;
    protected int maxNumber;
    private EnumSet<MathTaskGenerator.Operation> availableOperations;

    /**
     * @param minNumber              минимальное число
     * @param maxNumber              максимальное число
     * @param availableOperations    разрешённые операции
     */
    public AbstractMathTaskGenerator(int minNumber, int maxNumber, EnumSet<Operation> availableOperations) {
        if (minNumber >= maxNumber) {
            throw new IllegalArgumentException("Minimum cannot be greater than maximum");
        }
        this.minNumber = minNumber;
        this.maxNumber = maxNumber;
        this.availableOperations = availableOperations;
    }
    
    /**
     * @return                       рандомная допустимая операция
     * @param availableOperations    допустимые операции
     */
    public Operation getByRandomOperation() {
        if (availableOperations == null || availableOperations.isEmpty()) {
            throw new IllegalArgumentException("The availableOperations cannot be empty.");
        }
        int randomIndex = new Random().nextInt(availableOperations.size());
        int i = 0;
        for (MathTaskGenerator.Operation element : availableOperations) {
            if (i == randomIndex) {
                return element;
            }
            i++;
        }
        throw new IllegalStateException("Something went wrong while picking a random element.");
    }

    @Override
    public int getDiffNumber() {
        return maxNumber - minNumber;
    }

    @Override
    public int getMaxNumber() {
        return maxNumber;
    }

    @Override
    public int getMinNumber() {
        return minNumber;
    }

}