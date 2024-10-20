package by.mmaxemm.quizer.tasks;

import by.mmaxemm.quizer.Result;
import by.mmaxemm.quizer.Task;

public class EquationTask implements Task {
    final private int num1;
    final private int num2;
    final private String operator;

    public EquationTask(int num1, int answer, String operator) {
        if (!(operator.equals("+") ||
                operator.equals("-") ||
                operator.equals("*") ||
                operator.equals("/"))) {
            throw new IllegalArgumentException("Operator must be +, -, * or /");
        }
        this.num1 = num1;
        this.num2 = answer;
        this.operator = operator;
    }

    @Override
    public String getText() {
        //<num1><operator>x=<num2>
        return num1 + operator + "x=" + num1;
    }

    @Override
    public Result validate(String answer) {
        try {
        return switch(operator) {
            case "+" -> num2 - num1 == Integer.parseInt(answer) ? Result.OK : Result.WRONG;
            case "-" -> num1 + num1 == Integer.parseInt(answer) ? Result.OK : Result.WRONG;
            case "*" -> num2 / num1 == Integer.parseInt(answer) ? Result.OK : Result.WRONG;
            case "/" -> num1 * num1 == Integer.parseInt(answer) ? Result.OK : Result.WRONG;
            default -> throw new IllegalArgumentException("Operator must be +, -, * or /");
        }; } catch(NumberFormatException e) {
            return Result.INCORRECT_INPUT;
        }
    }

}