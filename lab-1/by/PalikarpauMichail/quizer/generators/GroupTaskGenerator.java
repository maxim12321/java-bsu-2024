package by.PalikarpauMichail.quizer.generators;

import java.util.List;
import java.util.ArrayList;
import java.util.Collection;

import by.PalikarpauMichail.quizer.IntegerRandom;
import by.PalikarpauMichail.quizer.Task;
import by.PalikarpauMichail.quizer.TaskGenerator;
import by.PalikarpauMichail.quizer.exceptions.TaskGenerationException;

public class GroupTaskGenerator<T extends Task> implements TaskGenerator<T> {
    /**
     * Функция, которая убирает из списка генераторы, бросающие исключения
     */
    private void FilterGenerators() {
        List<TaskGenerator<? extends T>> goodGenerators = new ArrayList<>();
        for (var generator : generators) {
            try {
                generator.generate();
                goodGenerators.add(generator);
            } catch(TaskGenerationException exception) {
            
            } 
        }
        this.generators = goodGenerators;
    }

    /**
     * Конструктор с переменным числом аргументов
     *
     * @param generators генераторы, которые в конструктор передаются через запятую
     */
    @SafeVarargs
    public GroupTaskGenerator(TaskGenerator<? extends T>... generators) {
        this.generators = List.of(generators);
        FilterGenerators();
    }

    /**
     * Конструктор, который принимает коллекцию генераторов
     *
     * @param generators генераторы, которые передаются в конструктор в Collection (например, {@link ArrayList})
     */
    public GroupTaskGenerator(Collection<TaskGenerator<? extends T>> generators) {
        this.generators = new ArrayList<>(generators);
        FilterGenerators();
    }
    
    /**
     * Генератор выбирает случайный генератор из списка, для создания задания
     * При пустом списке генераторов бросает исключение
     */
    @Override
    public T generate() throws TaskGenerationException { 
        if (generators.size() == 0) {
            throw new TaskGenerationException();
        }
        return generators.get(IntegerRandom.get(0, generators.size() - 1)).generate();
    } 

    List<TaskGenerator<? extends T>> generators;
}
