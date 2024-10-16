package by.KirillBukato.quizer.tasks;

import by.KirillBukato.quizer.Result;

public class TextVariantTask extends TextTask implements VariantTask {

    public TextVariantTask(String text, String a, String b, String c, VariantTask.Variants variant) {
        super(text, switch (variant) {
            case A -> a;
            case B -> b;
            case C -> c;
        });
        this.variants = new String[]{a, b, c};
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
    public Variants getVariant() {
        return variant;
    }

    public String[] getVariants() {
        return variants;
    }

    private final String[] variants;
    private final VariantTask.Variants variant;
}
