package by.KuryanovichNikita.quizer.generators;

import by.KuryanovichNikita.quizer.exceptions.NoAvailableOperatorsException;
import by.KuryanovichNikita.quizer.exceptions.NotValidGenerator;
import by.KuryanovichNikita.quizer.generators.math.AbstractMathTaskGenerator;
import by.KuryanovichNikita.quizer.tasks.EquationTask;
import by.KuryanovichNikita.quizer.tasks.ExpressionTask;
import by.KuryanovichNikita.quizer.tasks.math.MathTask;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Random;

public class ExpressionTaskGenerator extends AbstractMathTaskGenerator {

    /**
     * @param minNumber              минимальное число
     * @param maxNumber              максимальное число
     * @param operations             set из enum с 4 операциями
     */

    public ExpressionTaskGenerator(int minNumber, int maxNumber,
                                 EnumSet<MathTask.Operation> operations) {
        super(minNumber, maxNumber, operations);
    }


    /**
     * return задание типа {@link ExpressionTask}
     */
    public ExpressionTask generate() throws NotValidGenerator {
        if(minNumber > maxNumber) {
            throw new NotValidGenerator("Min > Max");
        }
        else if (minNumber == 0 && maxNumber == 0)
            throw new NotValidGenerator("minNumber == maxNumber == 0");
        Random random = new Random();
        int firstNum = getRandomNumber(random);
        int secondNum = getRandomNumber(random);
        String operator;
        List<String> trueIndices = getStringList();

        if (!trueIndices.isEmpty()) {
            int randomIndex = random.nextInt(trueIndices.size());
            operator = trueIndices.get(randomIndex);

            return new ExpressionTask(firstNum + operator + secondNum + "= ", firstNum, secondNum, operator);
        } else {
            throw new NoAvailableOperatorsException("ExpressionTaskGenerator cannot make " +
                    "ExpressionTask because there is no available operator");
        }

    }
    private List<String> getStringList() {
        List<String> trueIndices = new ArrayList<>();

        if (operations.contains(MathTask.Operation.ADDITION)) trueIndices.add(MathTask.Operation.ADDITION.getOperator());
        if (operations.contains(MathTask.Operation.SUBTRACTION)) trueIndices.add(MathTask.Operation.SUBTRACTION.getOperator());
        if (operations.contains(MathTask.Operation.MULTIPLICATION)) trueIndices.add(MathTask.Operation.MULTIPLICATION.getOperator());
        if (operations.contains(MathTask.Operation.DIVISION)) trueIndices.add(MathTask.Operation.DIVISION.getOperator());
        return trueIndices;
    }
}
