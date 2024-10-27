package by.maxsavitsky.quizer.generators;

import by.maxsavitsky.quizer.generators.math.AbstractMathTaskGenerator;
import by.maxsavitsky.quizer.tasks.EquationTask;
import by.maxsavitsky.quizer.tasks.math.MathTask;

import java.util.EnumSet;
import java.util.Random;

public class EquationTaskGenerator extends AbstractMathTaskGenerator<EquationTask> {

    private final Random random = new Random();

    public EquationTaskGenerator(int minNumber, int maxNumber, EnumSet<MathTask.Operation> operations) {
        super(minNumber, maxNumber, operations);
    }

    /**
     * @return задание типа {@link EquationTask}
     */
    @Override
    public EquationTask generate() {
        boolean leftHandedEquation = random.nextBoolean();
        char op = characters.get(random.nextInt(characters.size()));
        int number1 = generateNumberInBoundaries();
        int number2 = generateNumberInBoundaries();
        if (leftHandedEquation) {
            if (op == '/') {
                while (number2 == 0) {
                    number2 = generateNumberInBoundaries();
                }
            } else if (op == '*') {
                // number1 always divides number2
                number2 = number1 * random.nextInt(1, maxNumber / number1 + 1);
            }

            String text = "x" + op + (number1 < 0 ? "(" : "") + number1 + (number1 < 0 ? ")" : "") + "=" + number2;
            return new EquationTask(text, solveLH(number1, number2, op));
        }
        if (op == '*') {
            number2 = number1 * random.nextInt(1, maxNumber / number1 + 1);
        } else if (op == '/') {
            number1 = number2 * random.nextInt(1, maxNumber / number2 + 1);
        }
        String text = Integer.toString(number1) + op + "x=" + number2;
        return new EquationTask(text, solveRH(number1, number2, op));
    }

    /**
     * solve equations x(operator)(number1)=(number2)
     * */
    private int solveLH(int num1, int num2, char op) {
        if (op == '+') {
            return num2 - num1;
        }
        if (op == '-') {
            return num2 + num1;
        }
        if (op == '*') {
            return num2 / num1;
        }
        if (op == '/') {
            return num2 * num1;
        }
        throw new IllegalArgumentException("Unknown operator \"" + op + "\"");
    }

    /**
     * solve equations (number1)(operator)x=(number2)
     * */
    private int solveRH(int num1, int num2, char op) {
        if (op == '+') {
            return num2 - num1;
        }
        if (op == '-') {
            return num2 + num1;
        }
        if (op == '*') {
            return num2 / num1;
        }
        if (op == '/') {
            return num1 / num2;
        }
        throw new IllegalArgumentException("Unknown operator \"" + op + "\"");
    }

    private int generateNumberInBoundaries() {
        return random.nextInt(minNumber, maxNumber + 1);
    }
}
