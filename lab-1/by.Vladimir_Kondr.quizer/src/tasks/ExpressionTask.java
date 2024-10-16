package tasks;

import tasks.math.AbstractMathTask;

public class ExpressionTask extends AbstractMathTask {

    public ExpressionTask(int a, int b, Operation op) {
        super(op, a, b);
    }

    @Override
    protected int computeAnswer() {
        return op.perform(left, right);
    }

    @Override
    public boolean isValid() {
        return !(this.op == Operation.DIVISION && right == 0);
    }

    /**
     @return текст задания
     */
    @Override
    public String getText() {
        return left + " " + op.getSymbol() + " " + right + "= ?";
    }
}
