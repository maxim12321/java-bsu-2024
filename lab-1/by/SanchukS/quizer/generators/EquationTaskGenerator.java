package by.SanchukS.quizer.generators;

import by.SanchukS.quizer.Operation;
import by.SanchukS.quizer.generators.math.AbstractMathTaskGenerator;
import by.SanchukS.quizer.tasks.EquationTask;
import by.SanchukS.quizer.tasks.math.MathTask;

import java.util.EnumSet;
import java.util.Random;

class EquationTaskGenerator extends AbstractMathTaskGenerator {
    private final Random random = new Random();

    /**
     * @param minNumber              минимальное число
     * @param maxNumber              максимальное число
     * @param operations             набор разрешённых операций
     */
    EquationTaskGenerator(
            int minNumber,
            int maxNumber,
            EnumSet<Operation> operations
    ) {
        super(
                minNumber,
                maxNumber,
                operations
        );
    }

    /**
     * return задание типа {@link EquationTask}
     */
    public MathTask generate() {
        return new EquationTask(generateExpression(), random.nextBoolean());
    }
}