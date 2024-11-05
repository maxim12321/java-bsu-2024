package generators;

import exceptions.NumberGenerationException;
import generators.math.AbstractMathTaskGenerator;
import tasks.ExpressionTask;
import tasks.math.MathTask;

import java.util.EnumSet;
import java.util.Random;

public class ExpressionTaskGenerator extends AbstractMathTaskGenerator<ExpressionTask> {
    public ExpressionTaskGenerator(int minNumber, int maxNumber, EnumSet<MathTask.Operation> operations) {
        super(minNumber, maxNumber, operations);
    }

    public IllegalArgumentException isValid() {
        if (isOnlyDivision() && getMinNumber() == 0 && getMaxNumber() == 0) {
            return new IllegalArgumentException("Test will always have zero division");
        }
        return super.isValid();
    }

    /**
     * return задание типа {@link ExpressionTask}
     */
    @Override
    public ExpressionTask generate() throws NumberGenerationException {
        int tries = 1000;
        Random random = new Random();
        ExpressionTask task = null;
        boolean flag = false;
        do {
            try {
                task = new ExpressionTask(getRandomNumber(random),
                        getRandomNumber(random),
                        (MathTask.Operation) operations.toArray()[random.nextInt(operations.size())]);
                flag = true;
            } catch (IllegalArgumentException e) {
                continue;
            }
        } while (!flag && (--tries > 0));
        if (tries <= 0) {
            throw new NumberGenerationException("Unable to generate a task that meets the criteria after 1000 attempts");
        }
        return task;
    }
}