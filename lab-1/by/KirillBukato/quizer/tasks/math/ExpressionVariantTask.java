package by.KirillBukato.quizer.tasks.math;

import by.KirillBukato.quizer.Result;
import by.KirillBukato.quizer.tasks.VariantTask;


public class ExpressionVariantTask extends ExpressionTask implements VariantTask {

    public ExpressionVariantTask(int left, Operation operator, int right, double w1, double w2, VariantTask.Variants variant) {
        super(left, operator, right);
        switch (variant) {
            case A -> {
                a = super.ComputeAnswer();
                b = w1;
                c = w2;
            }
            case B -> {
                a = w1;
                b = super.ComputeAnswer();
                c = w2;
            }
            default -> {
                a = w1;
                b = w2;
                c = super.ComputeAnswer();
            }
        }
        this.variant = variant;
    }

    @Override
    public boolean isValid() {
        if (Math.abs(a - b) < 0.001 || Math.abs(a - c) < 0.001 || Math.abs(b - c) < 0.001) return false;
        return super.isValid();
    }

    @Override
    public String getText() {
        return getTextVariant();
    }

    @Override
    public Result validate(String answer) {
        return validateVariant(answer);
    }

    @Override
    public String getA() {
        return (Double.valueOf(a)).toString();
    }

    @Override
    public String getB() {
        return (Double.valueOf(b)).toString();
    }

    @Override
    public String getC() {
        return (Double.valueOf(c)).toString();
    }

    @Override
    public String getQuestion() {
        return super.getText();
    }

    @Override
    public Variants getVariant() {
        return variant;
    }

    private final Variants variant;
    private final double a;
    private final double b;
    private final double c;
}
