package by.pkasila.quizer.tasks;

import by.pkasila.quizer.common.Result;

public class TextVariantTask extends TextTask implements VariantTask {

    private final String[] answerVariants;

    private final TaskVariant variant;

    public TextVariantTask(String text, String[] answersVariants, TaskVariant variant) {
        super(text, answersVariants[variant.ordinal()]);
        this.answerVariants = answersVariants;
        this.variant = variant;
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
    public String getQuestion() {
        return super.getText();
    }

    @Override
    public TaskVariant getCorrectVariant() {
        return variant;
    }

    public String[] getAnswerVariants() {
        return answerVariants;
    }
}
