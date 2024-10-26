package KlimovMikh.quizer.generators.math;

import KlimovMikh.quizer.tasks.math.MathTask;

import java.util.EnumSet;
import java.util.Random;

public abstract class AbstractMathTaskGenerator<T extends MathTask> implements MathTaskGenerator<T> {
    protected int minNumber;
    protected int maxNumber;
    protected EnumSet<MathTask.Operation> operations;
    protected Random random;
    protected int number1;
    protected int number2;
    protected MathTask.Operation operation;

    /**
     * @param minNumber              минимальное число
     * @param maxNumber              максимальное число
     * @param operations            разрешить генерацию с операторами
     */
    public AbstractMathTaskGenerator(
            int minNumber,
            int maxNumber,
            EnumSet<MathTask.Operation> operations
    ) {
        if (minNumber > maxNumber) {
            throw new IllegalArgumentException("minNumber > maxNumber - incorrect arguments for generator");
        }
        this.minNumber = minNumber;
        this.maxNumber = maxNumber;
        this.operations = operations;
        this.random = new Random();
        this.number1 = 0;
        this.number2 = 0;
        this.operation = MathTask.Operation.PLUS;
    }

    @Override
    public int getMinNumber() {
        return minNumber;
    }

    @Override
    public int getMaxNumber() {
        return maxNumber;
    }

    protected void generateTask() {
        number1 = random.nextInt(maxNumber - minNumber) + minNumber;
        number2 = random.nextInt(maxNumber - minNumber) + minNumber;
        operation = (MathTask.Operation) operations.toArray()[random.nextInt(operations.size())];
        while (true) {
            if(operation.equals(MathTask.Operation.DIVIDE) && number2 == 0) {
                number2 = random.nextInt(minNumber, maxNumber);
            } else {
                break;
            }
        }
    }
}
