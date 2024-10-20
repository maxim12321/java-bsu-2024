package by.mmaxemm.quizer.tasks;
import by.mmaxemm.quizer.Result;
import by.mmaxemm.quizer.Task;

public class ExpressionTask implements Task {
    final private int num1;
    final private int num2;
    final private String operator;

    public ExpressionTask(int num1, int num2, String operator) {
        if (!(operator.equals("+") ||
                operator.equals("-") ||
                operator.equals("*") ||
                operator.equals("/"))) {
            throw new IllegalArgumentException("Operator must be +, -, * or /");
        }
        this.num1 = num1;
        this.num2 = num2;
        this.operator = operator;
    }

    @Override
    public String getText() {
        return num1 + operator + num2 + "=?";
    }

    @Override
    public Result validate(String answer) {
        try {
            return switch (operator) {
                case "+" -> num1 + num2 == Integer.parseInt(answer) ? Result.OK : Result.WRONG;
                case "-" -> num1 - num2 == Integer.parseInt(answer) ? Result.OK : Result.WRONG;
                case "*" -> num1 * num2 == Integer.parseInt(answer) ? Result.OK : Result.WRONG;
                case "/" -> num1 / num2 == Integer.parseInt(answer) ? Result.OK : Result.WRONG;
                default -> throw new IllegalArgumentException("Operator must be +, -, * or /");
            };
        } catch(NumberFormatException e) {
            return Result.INCORRECT_INPUT;
        }
    }
}
