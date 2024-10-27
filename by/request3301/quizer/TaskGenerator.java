package by.request3301.quizer;

import by.request3301.quizer.exceptions.GeneratorExhaustedException;

public interface TaskGenerator<T extends Task> {
    T generate() throws GeneratorExhaustedException;
}
