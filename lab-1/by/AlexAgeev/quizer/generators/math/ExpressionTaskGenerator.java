package by.AlexAgeev.quizer.generators.math;

import by.AlexAgeev.quizer.Task;
import by.AlexAgeev.quizer.TaskGenerator;
import by.AlexAgeev.quizer.exceptions.MinIsBiggerThanMaxException;
import by.AlexAgeev.quizer.tasks.math.AbstractMathTask;
import by.AlexAgeev.quizer.tasks.math.ExpressionTask;
import by.AlexAgeev.quizer.tasks.math.MathTask;

import java.util.ArrayList;
import java.util.Random;

public class ExpressionTaskGenerator extends AbstractMathTaskGenerator implements MathTaskGenerator{

    public ExpressionTaskGenerator(int minNumber, int maxNumber) {
        super(minNumber, maxNumber);
    }

    @Override
    public ExpressionTask generate() {
        if (this.minNumber > this.maxNumber) {
            throw new MinIsBiggerThanMaxException();
        }
        Random rand = new Random();
        int first = rand.nextInt(maxNumber - minNumber + 1) + minNumber;
        int second = rand.nextInt(maxNumber - minNumber + 1) + minNumber;
        MathTask.Operators operator;
        int opp = rand.nextInt(4);
        operator = switch (opp) {
            case 0 -> MathTask.Operators.ADD;
            case 1 -> MathTask.Operators.SUB;
            case 2 -> MathTask.Operators.DIV;
            default -> MathTask.Operators.MUL;
        };
        if (second == 0 && operator == MathTask.Operators.DIV) {
            while (second == 0) {
                second = rand.nextInt(maxNumber - minNumber + 1) + minNumber;
            }
        }
        return new ExpressionTask(first, second, operator);
    }
}
