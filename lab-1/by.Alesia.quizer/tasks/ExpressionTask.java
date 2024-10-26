package by.Alesia.quizer.tasks;

import by.Alesia.quizer.tasks.math.AbstractMathTask;

public class ExpressionTask extends AbstractMathTask {
    public ExpressionTask(int a, int b, Operation operation) {
        super(operation, a, b);
    }


    @Override
    public int answer() {
        return super.operation.MathOperation(super.first, super.second);
    }

    @Override
    public boolean IsValid() {
        return operation != Operation.DIV || second != 0;
    }

    @Override
    public String getText() {
        return first + operation.GetSymbol() + second + "=" + "?";
    }
}