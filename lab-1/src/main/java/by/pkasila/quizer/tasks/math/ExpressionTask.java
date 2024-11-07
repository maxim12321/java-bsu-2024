package by.pkasila.quizer.tasks.math;

import by.pkasila.quizer.common.MathOperation;

public class ExpressionTask extends AbstractExpressionTask {

    public ExpressionTask(int left, MathOperation operator, int right) {
        super(left, operator, right);
    }

    @Override
    public String getText() {
        return getQuestion();
    }

}
