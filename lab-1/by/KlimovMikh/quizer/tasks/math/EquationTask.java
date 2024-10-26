package KlimovMikh.quizer.tasks.math;

import KlimovMikh.quizer.Result;

public class EquationTask extends AbstractMathTask {
    private final boolean order;
    public EquationTask(int number1, int number2, Operation operation, boolean order) {
        super(number1, number2, operation);
        this.order = order;
    }

    @Override
    public String getText() {
        if (order) {
            return "x" + operation.toString() + number1 + "=" + number2;
        }
        return number1 + operation.toString() + "x=" + number2;
    }

    @Override
    public Result validate(String answer) {
        try {
            Integer.parseInt(answer);
        } catch (NumberFormatException e) {
            return Result.INCORRECT_INPUT;
        }
        if (order) {
            if(Integer.valueOf(answer).equals(operation.apply(true, number1, number2))) {
                return Result.OK;
            }
            return Result.WRONG;
        }
        boolean check = switch (operation) {
            case PLUS, MULTIPLY -> Integer.valueOf(answer).equals(operation.apply(true, number1, number2));
            case MINUS, DIVIDE -> Integer.valueOf(answer).equals(operation.apply(false, number1, number2));
        };
        if (check) {
            return Result.OK;
        }
        return Result.WRONG;
    }
}
