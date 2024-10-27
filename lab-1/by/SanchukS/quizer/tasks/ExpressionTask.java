package by.SanchukS.quizer.tasks;

import by.SanchukS.quizer.Result;
import by.SanchukS.quizer.Task;

public class ExpressionTask implements Task {
    private final int firstNumber;
    private final int secondNumber;
    private final String operation;
    private final int answer;


    public ExpressionTask(int firstNumber, String operation, int secondNumber) {
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
        this.operation = operation;
        this.answer = switch(operation) {
            case "+" -> this.firstNumber + this.secondNumber;
            case "-" -> this.firstNumber - this.secondNumber;
            case "*" -> this.firstNumber * this.secondNumber;
            case "/" -> this.firstNumber / this.secondNumber;
            default -> throw new IllegalArgumentException();
        };
    }

    @Override
    public String getText() {
        return firstNumber + " " + operation + " " + secondNumber + " = ?";
    }

    @Override
    public Result validate(String answer) {
        try {
            return Integer.parseInt(answer) == this.answer ? Result.OK : Result.WRONG;
        } catch (NumberFormatException e) {
            return Result.INCORRECT_INPUT;
        }
    }
}
