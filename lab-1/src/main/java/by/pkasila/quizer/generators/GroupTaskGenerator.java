package by.pkasila.quizer.generators;

import by.pkasila.quizer.exceptions.BadGeneratorException;
import by.pkasila.quizer.common.Task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class GroupTaskGenerator<T extends Task> implements TaskGenerator<T> {

    private final ArrayList<TaskGenerator<? extends T>> generators;

    @SafeVarargs
    public GroupTaskGenerator(TaskGenerator<? extends T>... generators) throws BadGeneratorException {
        this.generators = new ArrayList<>(Arrays.asList(generators));
        if (this.generators.isEmpty()) {
            throw new BadGeneratorException("GroupTaskGenerator must have at least one generator");
        }
    }

    public GroupTaskGenerator(Collection<TaskGenerator<? extends T>> generators) {
        this.generators = new ArrayList<>(generators);
    }

    @Override
    public T generate() throws RuntimeException {
        Collections.shuffle(generators);
        RuntimeException exception = new RuntimeException();
        for (TaskGenerator<? extends T> generator : generators) {
            try {
                return generator.generate();
            } catch (RuntimeException e) {
                exception = e;
            }
        }
        throw exception;
    }
}
