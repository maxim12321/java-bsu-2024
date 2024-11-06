package by.VadzimKamianetski.Quizer.TaskGenerators.Math;

import java.util.EnumSet;

import by.VadzimKamianetski.Quizer.TaskGenerators.Operation;
import by.VadzimKamianetski.Quizer.Tasks.Math.ExpressionTask;

public class ExpressionTaskGenerator extends AbstractMathTaskGenerator<ExpressionTask> {

    public ExpressionTaskGenerator(int minNumber, int maxNumber, EnumSet<Operation> availableOperations) {
        super(minNumber, maxNumber, availableOperations);
    }

    @Override
    public ExpressionTask generate() {
        Integer firstNumber = Random();
        Integer secondNumber = Random();
        Operation operation = getByRandomOperation();
        Integer answer = 42;
        switch (operation) {
            case GENERATESUM: 
                answer = (firstNumber + secondNumber);
                break;
            case GENERATEDIFFERENCE: 
                answer = (firstNumber - secondNumber);
                break;
            case GENERATEMULTIPLICATION: 
                answer = (firstNumber * secondNumber);
                break;
            case GENERATEDIVISION: 
                while (secondNumber == 0) {
                    secondNumber = Random();
                }
                firstNumber = divisionRandom(secondNumber);
                answer = (firstNumber / secondNumber);
                break;          
            default:
        }
        String text = firstNumber.toString() + operation.getSymbol() + Brackets(secondNumber) + "=?";
        return new ExpressionTask(text, answer.toString());
    }
}