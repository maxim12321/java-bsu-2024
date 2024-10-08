package by.KirillBukato.quizer.tasks.math;

public class ExpressionTask extends AbstractExpressionTask {

    public ExpressionTask(int left, MathTask.Operation operator, int right) {
        super(left, operator, right);
    }

    @Override
    public String getText() {
        return getQuestion();
    }

}
