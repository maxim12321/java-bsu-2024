package by.PalikarpauMichail.quizer.tasks;

import by.PalikarpauMichail.quizer.IntegerRandom;
import by.PalikarpauMichail.quizer.tasks.math.AbstractMathTask;

public class EquationTask extends AbstractMathTask{
    public EquationTask(
        int firstOperand,
        int secondOperand,
        int result,
        Operation operation
    ) {
        
        super(firstOperand, secondOperand, result, operation);
        if (IntegerRandom.get(0, 1) == 0) {
            this.firstOperand = null;
        } else {
            this.secondOperand = null;
        }
    }

    public String getText() {
        String text = new String();
        if (firstOperand == null) {
            text += "x";
        } else {
            text += firstOperand;
        }
        text += operation.toString();
        if (secondOperand == null) {
            text += "x";
        } else {
            text += secondOperand;
        }
        text += "=";
        text += result;
        return text;
    }

}
