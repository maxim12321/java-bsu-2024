package by.VeranikaFiliptsova.quizer.tasks.math;

import by.VeranikaFiliptsova.quizer.Operation;
import by.VeranikaFiliptsova.quizer.Result;
import by.VeranikaFiliptsova.quizer.Task;

public class EquationMathTask implements MathTask {
    int num1;
    int num2;
    Operation operation;
    int rightAnswer;
    boolean xStart;


    public EquationMathTask(int n1, Operation op, int n2, boolean xSt) {
        num1 = n1;
        num2 = n2;
        operation = op;
        xStart = xSt;
        rightAnswer = switch(op) {
            case SUM -> num2 - num1;
            case DIFF -> xStart ? num2 + num1 : num1 - num2;
            case MUL -> num2 / num1;
            case DIV -> xStart ? num1 * num2 : num1 / num2;
        };
    }


    @Override
    public String getText() {
        if (xStart) return "x"+Operation.myValueOf(operation)+num1+"="+num2;
        return num1+Operation.myValueOf(operation)+"x"+"="+num2;
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

    @Override
    public int calculate() {
        return 0;
    }
}
//ТЫ ЗАКОНЧИЛА ЗДЕСЬ
//Генерирует уравнения вида <num1><operator>x=<answer> и x<operator><num2>=<answer>. Например, x/2=6.