package by.KirillBukato.quizer.generators;

import by.KirillBukato.quizer.tasks.TextVariantTask;
import by.KirillBukato.quizer.tasks.VariantTask;

import java.util.Random;

/**
 * Текстовая задача с вариантами ответа
 */
public class TextVariantTaskGenerator implements VariantTaskGenerator<TextVariantTask> {
    /**
     * @param text    Текст задачи
     * @param correct Правильный ответ
     * @param wrong1  Первый неправильный ответ
     * @param wrong2  Второй неправильный ответ
     */
    public TextVariantTaskGenerator(String text, String correct, String wrong1, String wrong2) {
        this.text = text;
        this.correct = correct;
        this.wrong1 = wrong1;
        this.wrong2 = wrong2;
    }

    @Override
    public TextVariantTask generate() {
        Random random = new Random();
        return switch (random.nextInt(6)) {
            case 0 -> new TextVariantTask(text, correct, wrong1, wrong2, VariantTask.Variants.A);
            case 1 -> new TextVariantTask(text, correct, wrong2, wrong1, VariantTask.Variants.A);
            case 2 -> new TextVariantTask(text, wrong1, correct, wrong2, VariantTask.Variants.B);
            case 3 -> new TextVariantTask(text, wrong2, correct, wrong1, VariantTask.Variants.B);
            case 4 -> new TextVariantTask(text, wrong1, wrong2, correct, VariantTask.Variants.C);
            default -> new TextVariantTask(text, wrong2, wrong1, correct, VariantTask.Variants.C);
        };
    }

    private final String text;
    private final String correct;
    private final String wrong1;
    private final String wrong2;
}
