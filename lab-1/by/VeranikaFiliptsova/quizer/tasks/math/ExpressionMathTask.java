package by.VeranikaFiliptsova.quizer.tasks.math;

import by.VeranikaFiliptsova.quizer.Operation;
import by.VeranikaFiliptsova.quizer.Result;
import by.VeranikaFiliptsova.quizer.Task;

public class ExpressionMathTask implements Task {
    int num1;
    int num2;
    Operation operation;
    int rightAnswer;

    public ExpressionMathTask(int n1, Operation op, int n2) {
        num1 = n1;
        num2 = n2;
        operation = op;
        rightAnswer = switch(op) {
            case SUM -> n1 + n2;
            case DIFF -> n1 - n2;
            case MUL -> n1 * n2;
            case DIV -> n1 / n2;
        };
    }

    /*
    int compute() {
        return switch(operation) {
            case SUM -> num1 + num2;
            case DIFF -> num1 - num2;
            case MUL -> num1 * num2;
            case DIV -> num1 / num2;
        };
    }
    */

    @Override
    public String getText() {
        return num1 + Operation.myValueOf(operation) + num2 + "=";
    }

    @Override
    public Result validate(String answer) {
        int answerInt;
        try {
            answerInt = Integer.parseInt(answer);
        } catch (NumberFormatException ex) {
            System.out.println("Incorrect format of input");
            return Result.INCORRECT_INPUT;
        }
        if (answerInt == rightAnswer) {
            return Result.OK;
        }
        return Result.WRONG;
    }
}
