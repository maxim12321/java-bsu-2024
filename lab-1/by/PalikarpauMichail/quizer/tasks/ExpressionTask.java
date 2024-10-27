package by.PalikarpauMichail.quizer.tasks;

import by.PalikarpauMichail.quizer.tasks.math.AbstractMathTask;

public class ExpressionTask extends AbstractMathTask{
    public ExpressionTask(
        Integer firstOperand,
        Integer secondOperand,
        Integer result,
        Operation operation
    ) {
        super(firstOperand, secondOperand, null, operation);
    }

    @Override
    public String getText() {
        String text = new String(
            super.firstOperand.toString() +
            super.operation.toString() +
            super.secondOperand.toString() +
            "=?");
        return text;
    }

}
