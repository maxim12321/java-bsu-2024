package by.maxsavitsky.quizer.generators;

import by.maxsavitsky.quizer.generators.math.AbstractMathTaskGenerator;
import by.maxsavitsky.quizer.tasks.ExpressionTask;
import by.maxsavitsky.quizer.tasks.math.MathTask;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Random;

public class ExpressionTaskGenerator extends AbstractMathTaskGenerator<ExpressionTask> {

    private final Random random = new Random();

    public ExpressionTaskGenerator(int minNumber, int maxNumber, EnumSet<MathTask.Operation> operations) {
        super(minNumber, maxNumber, operations);
        if (minNumber == 0 && maxNumber == 0 && operations.size() == 1 && operations.contains(MathTask.Operation.DIVIDE)) {
            throw new IllegalArgumentException("Illegal arguments: tasks \"<num>/0\" will always be generated");
        }
    }

    /**
     * return задание типа {@link ExpressionTask}
     */
    @Override
    public ExpressionTask generate() {
        int number1 = random.nextInt(minNumber, maxNumber + 1);
        char character = characters.get(random.nextInt(characters.size()));
        int number2 = random.nextInt(minNumber, maxNumber + 1);
        if (character == '/' && number2 == '0') {
            return generate();
        }
        return new ExpressionTask(number1, number2, character);
    }

}
