package by.KirillBukato.quizer.generators.math;

import by.KirillBukato.quizer.exceptions.InvalidGeneratorException;
import by.KirillBukato.quizer.tasks.math.AbstractMathTask;
import by.KirillBukato.quizer.tasks.math.MathOperation;
import by.KirillBukato.quizer.tasks.math.MathTask;

import java.util.*;

/**
 * Абстрактный генератор математических задач
 *
 * @param <T> Тип задач
 */
public abstract class AbstractMathTaskGenerator<T extends MathTask> implements MathTaskGenerator<T> {

    /**
     * @param minNumber    минимальное число
     * @param maxNumber    максимальное число
     * @param operationArray множество разрешённых операций
     */
    public AbstractMathTaskGenerator(int minNumber,
                                     int maxNumber,
                                     EnumSet<MathOperation> operationArray) throws InvalidGeneratorException {
        this.minNumber = minNumber;
        this.maxNumber = maxNumber;
        this.operationArray = new ArrayList<>(operationArray);
        validateGenerator();
    }

    /**
     * В математических задачах могут генерироваться невалидные примеры (например, деление на ноль).
     * Интерфейс требует реализацию валидатора, который используется в конструкторе {@link AbstractMathTask}
     * Для всех валидаторов у математических задач есть общее условие:
     * минимальное число должно быть меньше максимального.
     */
    public void validateGenerator() throws InvalidGeneratorException {
        if (getMinNumber() > getMaxNumber()) {
            throw new InvalidGeneratorException("Min value is greater than Max value");
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

    /**
     * При генерации происходит валидация задачи. Если задача невалидна, она генерируется заново.
     * Этот процесс не может продолжаться бесконечно, поскольку если генератор создаёт только невалидные примеры,
     * это будет засечено валидатором генератора.
     */
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

    protected MathOperation getRandomOperation() {
        Random random = new Random();
        return operationArray.get(random.nextInt(operationArray.size()));
    }

    /**
     * Метод, необходимых для валидации генератора
     *
     * @return true если единственная операция это деление
     */
    protected boolean operationsIsDivision() {
        return EnumSet.copyOf(operationArray).equals(EnumSet.of(
                MathOperation.DIVIDE));
    }

    /**
     * Метод, необходимых для валидации генератора
     *
     * @return true если единственные операции это деление или умножение
     */
    protected boolean operationsIsDivisionAndMultiplication() {
        EnumSet<MathOperation> set = EnumSet.copyOf(operationArray);
        set.removeAll(EnumSet.of(MathOperation.DIVIDE, MathOperation.MULTIPLY));
        return set.isEmpty();
    }

    private final int minNumber;
    private final int maxNumber;
    private final ArrayList<MathOperation> operationArray;
}
