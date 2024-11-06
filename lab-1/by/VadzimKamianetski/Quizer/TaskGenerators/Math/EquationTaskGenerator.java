package by.VadzimKamianetski.Quizer.TaskGenerators.Math;

import java.util.EnumSet;

import by.VadzimKamianetski.Quizer.TaskGenerators.Operation;
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
        Integer firstNumber = Random();
        Integer secondNumber = Random();
        Operation operation = getByRandomOperation();
        Integer answer = 42;
        Position position = switch(rand.nextInt(2)) {
            case 0 -> Position.LEFT;        
            default -> Position.RIGHT;  
        }; 
        switch (operation) {
            case GENERATESUM:
                answer = -firstNumber + secondNumber; 
                break;
            case GENERATEDIFFERENCE: 
                if (position == Position.LEFT) {
                    answer = firstNumber + secondNumber;
                } else {
                    answer = firstNumber - secondNumber;
                }
                break;
            case GENERATEMULTIPLICATION:
                while (firstNumber == 0) {
                    firstNumber = Random();
                }
                answer = secondNumber / firstNumber;
                break;
            case GENERATEDIVISION: 
                while (firstNumber == 0) {
                    firstNumber = Random();
                }
                if (position == Position.LEFT) {
                    answer = firstNumber * secondNumber;
                } else {
                    while (secondNumber == 0) {
                        secondNumber = Random();
                    }
                    firstNumber = divisionRandom(secondNumber);
                    answer = firstNumber / secondNumber;
                }
                break;
            default:
        }
        String text = switch(position) {
            case LEFT -> "x" + operation.getSymbol() + Brackets(firstNumber);
            case RIGHT -> firstNumber.toString() + operation.getSymbol() + "x";
        } + "=" + secondNumber.toString();
        
        
        return new EquationTask(text, answer.toString());
    }
    
}
