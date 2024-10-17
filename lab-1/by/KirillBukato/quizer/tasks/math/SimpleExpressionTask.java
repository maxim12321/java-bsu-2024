package by.KirillBukato.quizer.tasks.math;

public class SimpleExpressionTask extends AbstractExpressionTask {

    public SimpleExpressionTask(int left, MathTask.Operation operator, int right) {
        super(left, operator, right);
    }

    @Override
    public String getText() {
        return getQuestion();
    }

}
