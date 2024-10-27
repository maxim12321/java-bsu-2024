package by.PalikarpauMichail.quizer.generators.math;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.AbstractMap;

import by.PalikarpauMichail.quizer.IntegerRandom;
import by.PalikarpauMichail.quizer.exceptions.BadMathGeneratorBoundariesException;
import by.PalikarpauMichail.quizer.exceptions.TaskGenerationException;
import by.PalikarpauMichail.quizer.tasks.math.MathTask;

abstract public class AbstractMathTaskGenerator implements MathTaskGenerator {
    public AbstractMathTaskGenerator(
        int minNumber,
        int maxNumber,
        EnumSet<MathTask.Operation> operations
    ) {
        if (minNumber > maxNumber) {
            throw new IllegalArgumentException();
        }
        this.minNumber = minNumber;
        this.maxNumber = maxNumber;
        this.operations = operations;

        if (operations.contains(MathTask.Operation.MULTIPLICATION) || operations.contains(MathTask.Operation.DIVISON)) {
            try {
                validateMultiplicationArgumentsGeneration();
            } catch(TaskGenerationException exception) {
                operations.remove(MathTask.Operation.MULTIPLICATION);
                operations.remove(MathTask.Operation.DIVISON);
            }
        }

        if (operations.contains(MathTask.Operation.ADDITION) || operations.contains(MathTask.Operation.SUBTRACTION)) {
            try {
                validateAdditionArgumentsGeneration();
            } catch(TaskGenerationException exception) {
                operations.remove(MathTask.Operation.ADDITION);
                operations.remove(MathTask.Operation.SUBTRACTION);
            }
        }

        if (operations.isEmpty()) {
            throw new BadMathGeneratorBoundariesException();
        }
    }


    @Override
    public int getMinNumber() {
        return minNumber;
    }

    @Override
    public int getMaxNumber() {
        return maxNumber;
    }

    @Override
    public int getRandomNumber() {
        return IntegerRandom.get(minNumber, maxNumber);
    }

    @Override
    public MathTask.Operation getRandomOperation() {
        return (MathTask.Operation)operations.toArray()[IntegerRandom.get(0, operations.size() - 1)];
    }

    /**
     * проверяет, что содержится ли число в границах [minNumber, maxNumber]
     */
    private boolean fitsLimits(int n) {
        return minNumber <= n && n <= maxNumber;
    }

    /**
     * @param n факторизуемое число
     * @return все делители числа, лежащие в границах
     */
    public List<Integer> Factorize(int n) {
        List<Integer> factors = new ArrayList<>();
    
        for (int i = 2; i <= (int)Math.sqrt(Math.abs(n)) + 1; i++) {
            if (n % i == 0 && fitsLimits(i) && fitsLimits(n / i)) {
                factors.add(i);
                factors.add(n / i);
            }
            if (n % i == 0 && fitsLimits(-i) && fitsLimits(n / (-i))) {
                factors.add(-i);
                factors.add(n / (-i));
            }
        }
        return factors;
    }


    /**
     * проверяет, что задачи на умножение/деление генерятся за нормальное число попыток
     */
    public boolean validateAdditionArgumentsGeneration() {
        for (int tryIndex = 0; tryIndex < maxGenerationTries; tryIndex++) {
            int a = getRandomNumber();
            int b = getRandomNumber();
            if (fitsLimits(a + b)) {
                return true;
            }
        } 
        throw new TaskGenerationException();
    }

    /**
     * проверяет, что задачи на умножение/деление генерятся за нормальное число попыток
     */
    public boolean validateMultiplicationArgumentsGeneration() {
        for (int tryIndex = 0; tryIndex < maxGenerationTries; tryIndex++) {
            int n = getRandomNumber();
            var factors = Factorize(n);
            if (!factors.isEmpty()) {
                return true;
            }
        } 
        throw new TaskGenerationException();
    }

    /**
     * проверяет, что задачи на умножение/деление генерятся за нормальное число попыток
     */
    public List<Integer> generateAdditionArguments() {
        while (true) {
            int a = getRandomNumber();
            int b = getRandomNumber();
            if (fitsLimits(a + b)) {
                return List.of(a, b, a + b);
            }
        } 
    }

    /**
     * проверяет, что задачи на умножение/деление генерятся за нормальное число попыток
     */
    public List<Integer> generateSubtractionArguments() {
        while (true) {
            int a = getRandomNumber();
            int b = getRandomNumber();
            if (fitsLimits(a + b)) {
                return List.of(a + b, a, b);
            }
        } 
    }

    /**
     *  Сгенерировать задачу на умножение
     */
    public List<Integer> generateMultiplicationArguments() {
        while (true) {
            int n = getRandomNumber();
            var factors = Factorize(n);
            if (!factors.isEmpty()) {
                int i = IntegerRandom.get(0, factors.size() - 1);
                return List.of(factors.get(i), n / factors.get(i), n);
            }
        }
    }

    public List<Integer> generateDivisionArguments() {
        var arguments = generateMultiplicationArguments();
        return List.of(arguments.get(2), arguments.get(0), arguments.get(1));
    }

    public AbstractMap.SimpleEntry<List<Integer>, MathTask.Operation> generateArguments() {
        List<MathTask.Operation> operationsList = new ArrayList<>(operations);
        if (operationsList.isEmpty()) {
            throw new TaskGenerationException();
        }
        int i = IntegerRandom.get(0, operationsList.size() - 1);
        
        List<Integer> arguments;
        switch(operationsList.get(i)) {
            case ADDITION -> {
                arguments = generateAdditionArguments();
            }
            case SUBTRACTION -> {
                arguments = generateSubtractionArguments();
            }
            case MULTIPLICATION -> {
                arguments = generateMultiplicationArguments();
            }
            case DIVISON -> {
                arguments = generateDivisionArguments();
            }
            default -> {
                return null;
            }
        }
        return new AbstractMap.SimpleEntry<>(arguments, operationsList.get(i));
    }

    abstract public MathTask generate();

    private static int maxGenerationTries = 100;

    private int minNumber;
    private int maxNumber;
    protected EnumSet<MathTask.Operation> operations;
}
