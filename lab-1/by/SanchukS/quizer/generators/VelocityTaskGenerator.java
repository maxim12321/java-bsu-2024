package by.SanchukS.quizer.generators;

import by.SanchukS.quizer.Expression;
import by.SanchukS.quizer.Operation;
import by.SanchukS.quizer.generators.math.AbstractMathTaskGenerator;
import by.SanchukS.quizer.tasks.VelocityTask;
import by.SanchukS.quizer.tasks.math.MathTask;

import java.util.EnumSet;
import java.util.Random;

public class VelocityTaskGenerator extends AbstractMathTaskGenerator {
    Random random = new Random();

    public VelocityTaskGenerator(int minNumber, int maxNumber) {
        super(minNumber, maxNumber, EnumSet.of(Operation.DIVIDE));
    }

    public MathTask generate() {
        Expression e = generateExpression();
        int velocity1 = random.nextInt(e.getNumber(1) - 1) + 1;
        int velocity2 = e.getNumber(1) - velocity1;
        return new VelocityTask(velocity1, velocity2, e.getNumber(0));
    }
}
