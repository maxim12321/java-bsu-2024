package by.VadzimKamianetski.Quizer.TaskGenerators.Math;

import java.util.EnumSet;

import by.VadzimKamianetski.Quizer.Tasks.Math.ExpressionTask;

public class ExpressionTaskGenerator extends AbstractMathTaskGenerator<ExpressionTask> {

    public ExpressionTaskGenerator(int minNumber, int maxNumber, EnumSet<Operation> availableOperations) {
        super(minNumber, maxNumber, availableOperations);
    }

    @Override
    public ExpressionTask generate() {
        Integer firstNumber = (int) (Math.random() * (maxNumber - minNumber + 1) + minNumber);
        Integer secondNumber = (int) (Math.random() * (maxNumber - minNumber + 1) + minNumber);
        MathTaskGenerator.Operation operation = getByRandomOperation();
        Integer answer = 42;
        String text = "Something went wrong";
        switch (operation) {
            case MathTaskGenerator.Operation.GENERATESUM: 
                answer = (firstNumber + secondNumber);
                text = firstNumber.toString() + '+' + secondNumber.toString() + "=?";
                break;
            case MathTaskGenerator.Operation.GENERATEDIFFERENCE: 
                answer = (firstNumber - secondNumber);
                text = firstNumber.toString() + '-' + secondNumber.toString() + "=?";
                break;
            case MathTaskGenerator.Operation.GENERATEMULTIPLICATION: 
                answer = (firstNumber * secondNumber);
                text = firstNumber.toString() + '*' + secondNumber.toString() + "=?";
                break;
            case MathTaskGenerator.Operation.GENERATEDIVISION: 
            while (secondNumber == 0) {
                secondNumber = (int) (Math.random() * (maxNumber - minNumber + 1) + minNumber);
            }
                answer = (firstNumber / secondNumber);
                text = firstNumber.toString() + '/' + secondNumber.toString() + "=?";
                break;          
            default:
        }
        return new ExpressionTask(text, answer.toString());
    }
}