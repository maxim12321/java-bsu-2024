package by.AlexAgeev.quizer.generators.math;

import by.AlexAgeev.quizer.exceptions.MinIsBiggerThanMaxException;
import  by.AlexAgeev.quizer.tasks.math.EquationTask;
import by.AlexAgeev.quizer.tasks.math.MathTask;

import java.util.Random;

public class EquationTaskGenerator extends AbstractMathTaskGenerator implements MathTaskGenerator {

        public EquationTaskGenerator(int minNumber, int maxNumber) {
            super(minNumber, maxNumber);
        }

    @Override
    public EquationTask generate() {
            if (this.minNumber > this.maxNumber) {
                throw new MinIsBiggerThanMaxException();
            }
        Random rand = new Random();
        int first = rand.nextInt(maxNumber - minNumber + 1) + minNumber;
        int second = rand.nextInt(maxNumber - minNumber + 1) + minNumber;
        MathTask.Operators operator;
        int opp = rand.nextInt(4);
        int pos = rand.nextInt(2);
        operator = switch (opp) {
            case 0 -> MathTask.Operators.ADD;
            case 1 -> MathTask.Operators.SUB;
            case 2 -> MathTask.Operators.DIV;
            default -> MathTask.Operators.MUL;
        };
        if (pos == 1 && (second == 0 || first == 0) && operator == MathTask.Operators.DIV) {
            while (second == 0) {
                second = rand.nextInt(maxNumber - minNumber + 1) + minNumber;
            }
        }

        return new EquationTask(pos, first, second, operator);
    }

}
