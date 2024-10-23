package generators;

import exceptions.NumberGenerationException;
import generators.math.AbstractMathTaskGenerator;
import tasks.EquationTask;
import tasks.math.MathTask;


import java.util.EnumSet;
import java.util.Random;

public class EquationTaskGenerator extends AbstractMathTaskGenerator<EquationTask> {
    public EquationTaskGenerator(int minNumber, int maxNumber, EnumSet<MathTask.Operation> operations) {
        super(minNumber, maxNumber, operations);
    }

    /**
     * return задание типа {@link EquationTask}
     */
    @Override
    public EquationTask generate() {
        int tries = 1000;
        Random random = new Random();
        EquationTask task = null;
        boolean flag = false;
        do {
            try {
                task = new EquationTask(getRandomNumber(random),
                        (MathTask.Operation) operations.toArray()[random.nextInt(operations.size())],
                        getRandomNumber(random),
                        random.nextBoolean());
                flag = true;
            } catch (IllegalArgumentException e) {
                continue;
            }
        } while (!flag && --tries > 0);
        if (tries <= 0) {
            throw new NumberGenerationException("Unable to generate a task that meets the criteria after 1000 attempts");
        }
        return task;
    }
}