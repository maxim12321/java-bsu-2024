package by.pkasila.quizer.generators;

import by.pkasila.quizer.tasks.TaskVariant;
import by.pkasila.quizer.tasks.TextVariantTask;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Текстовая задача с вариантами ответа
 */
public class TextVariantTaskGenerator implements VariantTaskGenerator<TextVariantTask> {
    private final String text;

    private final String correct;

    private final String wrong1;

    private final String wrong2;

    public TextVariantTaskGenerator(String text, String correct, String wrong1, String wrong2) {
        this.text = text;
        this.correct = correct;
        this.wrong1 = wrong1;
        this.wrong2 = wrong2;
    }

    @Override
    public TextVariantTask generate() {
        Random random = new Random();
        TaskVariant variant = TaskVariant.values()[random.nextInt(3)];
        ArrayList<String> variants = new ArrayList<>(List.of(wrong1, wrong2));
        Collections.shuffle(variants);
        variants.add(variant.ordinal(), correct);
        return new TextVariantTask(text, variants.toArray(String[]::new), variant);
    }
}
