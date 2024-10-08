package by.KirillBukato.quizer.generators.math;

import by.KirillBukato.quizer.tasks.math.MathTask;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Random;

/**
 * Абстрактный генератор математических задач
 * @param <T>   Тип задач
 */
public abstract class AbstractMathTaskGenerator<T extends MathTask> implements MathTaskGenerator<T> {

    /**
     * @param minNumber минимальное число
     * @param maxNumber максимальное число
     * @param enumSet   множество разрешённых операций
     */
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

    /**
     * Для всех валидаторов у математических задач есть общее условие:
     * минимальное число должно быть меньше максимального.
     */
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

    protected MathTask.Operation getRandomOperation() {
        Random random = new Random();
        return operators.get(random.nextInt(operators.size()));
    }

    /**
     * Метод, необходимых для валидации генератора
     * @return  true если единственная операция это деление
     */
    protected boolean operationsIsDivision() {
        return EnumSet.copyOf(operators).equals(EnumSet.of(
                MathTask.Operation.DIVIDE));
    }

    /**
     * Метод, необходимых для валидации генератора
     * @return  true если единственные операции это деление или умножение
     */
    protected boolean operationsIsDivisionAndMultiplication() {
        EnumSet<MathTask.Operation> set = EnumSet.copyOf(operators);
        set.removeAll(EnumSet.of(MathTask.Operation.DIVIDE, MathTask.Operation.MULTIPLY));
        return set.isEmpty();
    }

    private final int minNumber;
    private final int maxNumber;
    private final ArrayList<MathTask.Operation> operators;
}
