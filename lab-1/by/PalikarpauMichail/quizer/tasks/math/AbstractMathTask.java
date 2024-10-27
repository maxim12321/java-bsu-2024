package by.PalikarpauMichail.quizer.tasks.math;
import by.PalikarpauMichail.quizer.Result;

abstract public class AbstractMathTask implements MathTask {
    public AbstractMathTask(
        Integer firstOperand,
        Integer secondOperand,
        Integer result,
        Operation operation
    ) {
        this.firstOperand = firstOperand;
        this.secondOperand = secondOperand;
        this.result = result;
        this.operation = operation;
    }

    private Result validateExpression(
        Integer firstOperand,
        Integer secondOperand,
        Integer result,
        Operation operation
    ) {
        if (MathTask.Calculate(firstOperand, secondOperand, operation) == result) {
            return Result.OK;
        } else {
            return Result.WRONG;
        }
    }

    @Override
    public Result validate(String answer) {
        /*
         * Возвращает информацию о неправильном вводе, если answer неприводимо к Integer
         */
        try {
            Integer.valueOf(answer);
        } catch(NumberFormatException exception) {
            return Result.INCORRECT_INPUT;
        }

        /*
         * Вычисляет выражение и возвращает Result.OK, если выражение обращается в верное
         * Возвращает Result.WRONG, если в процессе вычислений возникли исключения, или выражение стало неверным
         */

        try {
            if (firstOperand == null) {
                return validateExpression(Integer.valueOf(answer), secondOperand, result, operation);
            } else if (secondOperand == null) {
                return validateExpression(firstOperand, Integer.valueOf(answer), result, operation);
            } else if (result == null) {
                return validateExpression(firstOperand, secondOperand, Integer.valueOf(answer), operation);
            } else {
                throw new RuntimeException();
            }
        } catch(Exception exception) {
            return Result.WRONG;
        }
    }

    abstract public String getText();

    protected Integer firstOperand;
    protected Integer secondOperand;
    protected Integer result;
    protected Operation operation;
}
