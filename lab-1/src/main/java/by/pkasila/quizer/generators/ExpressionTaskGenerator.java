package by.pkasila.quizer.generators;

import by.pkasila.quizer.TaskGenerator;
import by.pkasila.quizer.common.Operation;
import by.pkasila.quizer.exceptions.QuizException;
import by.pkasila.quizer.generators.math.MathTaskGenerator;
import by.pkasila.quizer.tasks.ExpressionTask;

import java.util.EnumSet;

public class ExpressionTaskGenerator<T extends Number> implements TaskGenerator, MathTaskGenerator {
    /**
     * @param minNumber              минимальное число
     * @param maxNumber              максимальное число
     * @param allowedOperations      перечень разрешенных операций
     */
    ExpressionTaskGenerator(
            int minNumber,
            int maxNumber,
            EnumSet<Operation> allowedOperations
    ) {
        // ...
    }

    /**
     * return задание типа {@link ExpressionTask}
     */
    public ExpressionTask generate() {
        throw new QuizException("not implemented");
    }

    /**
     * @return
     */
    @Override
    public int getMinNumber() {
        return 0;
    }

    /**
     * @return
     */
    @Override
    public int getMaxNumber() {
        return 0;
    }
}
