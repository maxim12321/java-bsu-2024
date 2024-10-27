package by.MikitaShutro.generators;

import by.MikitaShutro.TaskGenerator;
import by.MikitaShutro.tasks.AbstractTask;

public interface MathTaskGenerator<T extends AbstractTask> extends TaskGenerator<T> {
        Integer getMinNumber(); // получить минимальное число
        Integer getMaxNumber(); // получить максимальное число

        default Integer getDiffNumber() {
            return getMaxNumber() - getMinNumber();
        }
}
