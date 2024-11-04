package by.KirillBukato.quizer.tasks.math;

import by.KirillBukato.quizer.Result;
import by.KirillBukato.quizer.tasks.TaskVariant;
import by.KirillBukato.quizer.tasks.VariantTask;


public class VariantExpressionTask extends AbstractExpressionTask implements VariantTask {

    public VariantExpressionTask(int left, MathOperation operator, int right, double w1, double w2, TaskVariant variant) {
        super(left, operator, right);
        variants = switch (variant) {
            case A -> new Double[]{computeAnswer(), w1, w2};
            case B -> new Double[]{w1, computeAnswer(), w2};
            case C -> new Double[]{w1, w2, computeAnswer()};
        };
        this.variant = variant;
    }

    /**
     * Проверка на то, что варианты ответа разные
     */
    @Override
    public boolean isValid() {
        if (Math.abs(variants[0] - variants[1]) < 0.001 || Math.abs(variants[0] - variants[2]) < 0.001 || Math.abs(variants[1] - variants[2]) < 0.001)
            return false;
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
    public TaskVariant getCorrectVariant() {
        return variant;
    }

    @Override
    public String[] getAnswerVariants() {
        return new String[]{variants[0].toString(), variants[1].toString(), variants[2].toString()};
    }

    private final TaskVariant variant;
    private final Double[] variants;
}
