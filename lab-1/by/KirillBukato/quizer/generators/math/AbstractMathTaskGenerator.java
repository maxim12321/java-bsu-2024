package by.KirillBukato.quizer.generators.math;

import by.KirillBukato.quizer.exceptions.InvalidGeneratorException;
import by.KirillBukato.quizer.tasks.math.AbstractMathTask;
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
     * @param operationSet множество разрешённых операций
     */
    public AbstractMathTaskGenerator(int minNumber,
                                     int maxNumber,
                                     EnumSet<MathTask.Operation> operationSet) throws InvalidGeneratorException {
        this.minNumber = minNumber;
        this.maxNumber = maxNumber;
        this.operationSet = operationSet;
        Optional<InvalidGeneratorException> e = validateGenerator();
        if (e.isPresent()) {
            throw e.get();
        }
    }

    /**
     * В математических задачах могут генерироваться невалидные примеры (например, деление на ноль).
     * Интерфейс требует реализацию валидатора, который используется в конструкторе {@link AbstractMathTask}
     * Для всех валидаторов у математических задач есть общее условие:
     * минимальное число должно быть меньше максимального.
     *
     * @return Исключение при невалидном генераторе (или null при валидном)
     */
    public Optional<InvalidGeneratorException> validateGenerator() {
        if (getMinNumber() > getMaxNumber()) {
            return Optional.of(new InvalidGeneratorException("Min value is greater than Max value"));
        }
        return Optional.empty();
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
        int index = random.nextInt(operationSet.size());
        var iter = operationSet.iterator();
        for (int i = 0; i < index; i++) {
            iter.next();
        }
        return iter.next();
    }

    /**
     * Метод, необходимых для валидации генератора
     *
     * @return true если единственная операция это деление
     */
    protected boolean operationsIsDivision() {
        return EnumSet.copyOf(operationSet).equals(EnumSet.of(
                MathTask.Operation.DIVIDE));
    }

    /**
     * Метод, необходимых для валидации генератора
     *
     * @return true если единственные операции это деление или умножение
     */
    protected boolean operationsIsDivisionAndMultiplication() {
        EnumSet<MathTask.Operation> set = EnumSet.copyOf(operationSet);
        set.removeAll(EnumSet.of(MathTask.Operation.DIVIDE, MathTask.Operation.MULTIPLY));
        return set.isEmpty();
    }

    private final int minNumber;
    private final int maxNumber;
    private final EnumSet<MathTask.Operation> operationSet;
}
