package KlimovMikh.quizer.generators.math;

import KlimovMikh.quizer.tasks.math.EquationTask;
import KlimovMikh.quizer.tasks.math.MathTask;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Random;

import static java.lang.Math.abs;

public class EquationTaskGenerator extends AbstractMathTaskGenerator<EquationTask> {
    public EquationTaskGenerator(
            int minNumber,
            int maxNumber,
            EnumSet<MathTask.Operation> operations
    ) {
        super(minNumber, maxNumber, operations);
    }

    public EquationTask generate() {
        generateTask();
        Random rand = new Random();
        boolean order = rand.nextBoolean();
        boolean sign = rand.nextBoolean();
        if (operation.equals(MathTask.Operation.MULTIPLY) || (operation.equals(MathTask.Operation.DIVIDE) && !order)) {
            if (number2 % number1 != 0) {
                ArrayList<Integer> divisors = new ArrayList<>();
                for (int i = 1; i < abs(number2) / 2; ++i) {
                    if (number2 % i == 0) {
                        divisors.add(i);
                    }
                }
                Collections.shuffle(divisors);
                number1 = divisors.getFirst();
                if (sign && (-1 * number1 >= minNumber)) {
                    number1 *= -1;
                }
            }
        }
        if(operation.equals(MathTask.Operation.MULTIPLY)) {
            return new EquationTask(number1, number2, operation, order);
        }
        return new EquationTask(number2, number1, operation, order);
    }
}

