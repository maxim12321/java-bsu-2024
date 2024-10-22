package by.VadzimKamianetski.Quizer.TaskGenerators.Math;

import java.util.Random;
import java.util.EnumSet;

import by.VadzimKamianetski.Quizer.Operation;
import by.VadzimKamianetski.Quizer.Tasks.Math.EquationTask;

public class EquationTaskGenerator extends AbstractMathTaskGenerator<EquationTask> {
    enum Position {
        LEFT,
        RIGHT
    }

    public EquationTaskGenerator(int minNumber, int maxNumber, EnumSet<Operation> availableOperations) {
        super(minNumber, maxNumber, availableOperations);
    }

    @Override
    public EquationTask generate() {
        Random rand = new Random();
        Integer firstNumber = rand.nextInt(maxNumber - minNumber + 1) + minNumber;
        Integer secondNumber = rand.nextInt(maxNumber - minNumber + 1) + minNumber;
        Operation operation = getByRandomOperation();
        Integer answer = 42;
        Position position = switch(rand.nextInt(2)) {
            case 0 -> Position.LEFT;        
            default -> Position.RIGHT;  
        }; 
        switch (operation) {
            case Operation.GENERATESUM:
                answer = -firstNumber + secondNumber; 
                break;
            case Operation.GENERATEDIFFERENCE: 
                if (position == Position.LEFT) {
                    answer = firstNumber + secondNumber;
                } else {
                    answer = firstNumber - secondNumber;
                }
                break;
            case Operation.GENERATEMULTIPLICATION:
                while (firstNumber == 0) {
                    firstNumber = rand.nextInt(maxNumber - minNumber + 1) + minNumber;
                }
                answer = secondNumber / firstNumber;
                break;
            case Operation.GENERATEDIVISION: 
                while (firstNumber == 0) {
                    firstNumber = rand.nextInt(maxNumber - minNumber + 1) + minNumber;
                }
                if (position == Position.LEFT) {
                    answer = firstNumber * secondNumber;
                } else {
                    while (secondNumber == 0) {
                        secondNumber = rand.nextInt(maxNumber - minNumber + 1) + minNumber;
                    }
                    firstNumber = divisionRandom(rand, secondNumber);
                    answer = firstNumber / secondNumber;
                }
                break;
            default:
        }
        String text = switch(position) {
            case Position.LEFT -> "x" + operation.getSymbol() + Brackets(firstNumber);
            case Position.RIGHT -> firstNumber.toString() + operation.getSymbol() + "x";
        } + "=" + secondNumber.toString();
        
        
        return new EquationTask(text, answer.toString());
    }
    
}
