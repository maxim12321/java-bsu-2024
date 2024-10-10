package by.KirillBukato.quizer.generators.math;

import by.KirillBukato.quizer.TaskGenerator;
import by.KirillBukato.quizer.tasks.math.MathTask;

/**
 * Общий интерфейс для математических задач
 * @param <T>   Тип задачи
 */
public interface MathTaskGenerator<T extends MathTask> extends TaskGenerator<T> {
    
    int getMinNumber();
    
    int getMaxNumber();

    /**
     * Абстрактный класс, наследуемый от интерфейса, реализует generate() посредством создания
     * непроверенной задачи посредством generateUnvalidated() и проверки с помощью isValid()
     */
    T generateUnvalidated();

    /**
     * @return разница между максимальным и минимальным возможным числом
     */
    default int getDiffNumber() {
        return getMaxNumber() - getMinNumber();
    }
}
