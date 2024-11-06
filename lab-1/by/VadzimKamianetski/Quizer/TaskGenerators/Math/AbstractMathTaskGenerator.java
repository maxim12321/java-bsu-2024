package by.VadzimKamianetski.Quizer.TaskGenerators.Math;

import java.util.EnumSet;
import java.util.Random;

import by.VadzimKamianetski.Quizer.TaskGenerators.Operation;
import by.VadzimKamianetski.Quizer.Tasks.Math.MathTask;

public abstract class AbstractMathTaskGenerator<T extends MathTask> implements MathTaskGenerator<T> {

    @Override
    public Integer Random() {
        return rand.nextInt(minNumber, maxNumber + 1);
    }

    @Override
    public Integer divisionRandom(Integer num) {
        if (num > 0) {
            return num * rand.nextInt(maxNumber/num + 1);
        }
        return num * rand.nextInt(minNumber/num + 1);
    }

    @Override
    public String Brackets(Integer num) {
        if (num < 0) {
            return "("+ num.toString() + ")";
        }
        return num.toString();
    }

    final protected Random rand = new Random();
    protected int minNumber;
    protected int maxNumber;
    private EnumSet<Operation> availableOperations;

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
        // int randomIndex = new Random().nextInt(availableOperations.size());
        int i = 0;
        for (Operation element : availableOperations) {
            if (i == rand.nextInt(availableOperations.size())) {
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