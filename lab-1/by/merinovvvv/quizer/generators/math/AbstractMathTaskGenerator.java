package by.merinovvvv.quizer.generators.math;

import by.merinovvvv.quizer.tasks.math.MathTask;

abstract public class AbstractMathTaskGenerator implements MathTaskGenerator {

    @Override
    public Object[] generateMathTask(int maxNumber, int minNumber,
                                     MathTask.Operation operation, boolean monkey) {
        int num1 = (int) (Math.random() * (maxNumber - minNumber + 1) + minNumber);
        int num2 = (int) (Math.random() * (maxNumber - minNumber + 1) + minNumber);
        int num3 = (int) (Math.random() * (maxNumber - minNumber + 1) + minNumber);
        try {
            String operator = getString(operation);

            if (operator.equals("*") && ((num1 == 0 || num2 == 0) && num3 != 0) || (operator.equals("/") && num1 == 0 && num3 != 0) || (operator.equals("/") && num2 == 0)) {
                return generateMathTask(maxNumber, minNumber, operation, monkey);
            }

            Object[] arrayToReturn = new Object[4];
            arrayToReturn[0] = num1;
            arrayToReturn[1] = num2;
            arrayToReturn[2] = operator;
            if (monkey) {
                return arrayToReturn;
            }
            arrayToReturn[3] = num3;
            return arrayToReturn;
        } catch (IllegalArgumentException e) {
            e.getMessage();
            System.exit(1);
            return null;
        }
    }

    static String getString(MathTask.Operation operation) {
        String operator;
        switch (operation) {
            case ADDITION -> operator = "+";
            case SUBTRACTION -> operator = "-";
            case MULTIPLICATION -> operator = "*";
            case DIVISION -> operator = "/";

            default -> throw new IllegalArgumentException("Unknown operation: " + operation);
        }

        return operator;
    }
}
