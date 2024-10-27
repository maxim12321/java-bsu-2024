package by.PalikarpauMichail.quizer.tasks;

import by.PalikarpauMichail.quizer.Result;

public class TextMathTask extends ExpressionTask {
    String text;

    public TextMathTask(
        String text,        
        Integer firstOperand,
        Integer secondOperand,
        Integer result,
        Operation operation)
    {
        super(firstOperand, secondOperand, result, operation);
        this.text = String.format(text, firstOperand, secondOperand);
    }

    @Override
    public Result validate(String answer) {
        return super.validate(answer);
    }

    @Override
    public String getText() {
        return text;
    }
}
