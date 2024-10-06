package by.KirillBukato.quizer.tasks;

import by.KirillBukato.quizer.Result;

public class TextVariantTask extends TextTask implements VariantTask {

    public TextVariantTask(String text, String a, String b, String c, VariantTask.Variants variant) {
        super(text, switch (variant) {
            case A -> a;
            case B -> b;
            case C -> c;
        });
        this.a = a;
        this.b = b;
        this.c = c;
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

    @Override
    public String getA() {
        return a;
    }

    @Override
    public String getB() {
        return b;
    }

    @Override
    public String getC() {
        return c;
    }

    private final String a;
    private final String b;
    private final String c;
    private final VariantTask.Variants variant;
}
