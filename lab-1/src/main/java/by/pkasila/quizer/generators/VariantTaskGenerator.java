package by.pkasila.quizer.generators;

import by.pkasila.quizer.tasks.TaskVariant;
import by.pkasila.quizer.tasks.VariantTask;

import java.util.Random;

public interface VariantTaskGenerator<T extends VariantTask> extends TaskGenerator<T> {
    default TaskVariant getRandomVariant() {
        Random random = new Random();
        return TaskVariant.values()[random.nextInt(3)];
    }
}
