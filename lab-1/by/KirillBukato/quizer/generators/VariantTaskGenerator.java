package by.KirillBukato.quizer.generators;

import by.KirillBukato.quizer.TaskGenerator;
import by.KirillBukato.quizer.tasks.VariantTask;

import java.util.Random;

/**
 * Общий интерфейс для задач с вариантом ответа
 * @param <T>       Тип задачи с вариантом
 */
public interface VariantTaskGenerator<T extends VariantTask> extends TaskGenerator<T> {

    default VariantTask.Variants getRandomVariant() {
        Random random = new Random();
        return switch (random.nextInt(3)) {
            case 0 -> VariantTask.Variants.A;
            case 1 -> VariantTask.Variants.B;
            default -> VariantTask.Variants.C;
        };
    }

}
