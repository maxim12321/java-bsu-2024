package by.Alesia.quizer.tasks;

import by.Alesia.quizer.tasks.math.AbstractMathTask;

public class EquationTask extends AbstractMathTask {
    public EquationTask(int a, int b, Operation operation) {
        super(operation, a, b);
    }


    @Override
    public int answer() {
        return super.operation.ReverseOperation().MathOperation(super.second, super.first);
    }

    @Override
    public boolean IsValid() {
        return operation != Operation.MUL || first != 0 || second == 0;
    }

    @Override
    public String getText() {
        return "x" + operation.GetSymbol() + first + "=" + second;
    }
}