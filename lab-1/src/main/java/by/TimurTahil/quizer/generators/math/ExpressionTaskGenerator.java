package by.TimurTahil.quizer.generators.math;

import by.TimurTahil.quizer.tasks.math.ExpressionTask;
import by.TimurTahil.quizer.tasks.math.MathTask;

import java.util.Random;

public class ExpressionTaskGenerator extends AbstractMathTaskGenerator implements MathTaskGenerator {

    public ExpressionTaskGenerator(int minNumber, int maxNumber) {
        super(minNumber, maxNumber);
    }

    @Override
    public ExpressionTask generate() {
        Random rand = new Random();
        final int firstNumber = rand.nextInt(maxNumber - minNumber + 1) + minNumber;
        int secondNumber = rand.nextInt(maxNumber - minNumber + 1) + minNumber;
        final int operatorIndex = rand.nextInt(4);
        final MathTask.Operators operator = switch (operatorIndex) {
            case 0 -> MathTask.Operators.SUM;
            case 1 -> MathTask.Operators.SUBTRACTION;
            case 2 -> MathTask.Operators.DIVISION;
            case 3 -> MathTask.Operators.MULTIPLICATION;
            default -> throw new RuntimeException("random from 0 to 3 is not working");
        };
        if (operator == MathTask.Operators.DIVISION) {
            while (secondNumber == 0) {
                secondNumber = rand.nextInt(maxNumber - minNumber + 1) + minNumber;
            }
        }
        return new ExpressionTask(firstNumber, secondNumber, operator);
    }
}