package by.KirillBukato.quizer.generators;

import by.KirillBukato.quizer.tasks.TaskVariant;
import by.KirillBukato.quizer.tasks.VariantTask;

import java.util.Random;

/**
 * Общий интерфейс для задач с вариантом ответа
 *
 * @param <T> Тип задачи с вариантом
 */
public interface VariantTaskGenerator<T extends VariantTask> extends TaskGenerator<T> {
    default TaskVariant getRandomVariant() {
        Random random = new Random();
        return TaskVariant.values()[random.nextInt(3)];
    }
}
