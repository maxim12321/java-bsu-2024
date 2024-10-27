package by.TimurTahil.quizer.generators.math;

import by.TimurTahil.quizer.tasks.math.EquationTask;
import by.TimurTahil.quizer.tasks.math.MathTask;

import java.util.Random;

public class EquationTaskGenerator extends AbstractMathTaskGenerator implements MathTaskGenerator {

    public EquationTaskGenerator(int minNumber, int maxNumber) {
        super(minNumber, maxNumber);
    }

    @Override
    public EquationTask generate() {
        Random rand = new Random();
        int firstNumber = rand.nextInt(maxNumber - minNumber + 1) + minNumber;
        int secondNumber = rand.nextInt(maxNumber - minNumber + 1) + minNumber;
        final int operatorIndex = rand.nextInt(4);
        final MathTask.Operators operator = switch (operatorIndex) {
            case 0 -> MathTask.Operators.SUM;
            case 1 -> MathTask.Operators.SUBTRACTION;
            case 2 -> MathTask.Operators.DIVISION;
            case 3 -> MathTask.Operators.MULTIPLICATION;
            default -> throw new RuntimeException("random from 0 to 3 is not working");
        };
        if (operator == MathTask.Operators.MULTIPLICATION || operator == MathTask.Operators.DIVISION) {
            while (secondNumber == 0) {
                secondNumber = rand.nextInt(maxNumber - minNumber + 1) + minNumber;
            }
            while (firstNumber == 0) {
                firstNumber = rand.nextInt(maxNumber - minNumber + 1) + minNumber;
            }
        }
        final int position = rand.nextInt(2);
        return new EquationTask(position, firstNumber, secondNumber, operator);
    }

}
