package by.maxsavitsky.quizer.generators.math;

import by.maxsavitsky.quizer.tasks.math.MathTask;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public abstract class AbstractMathTaskGenerator<T extends MathTask> implements MathTaskGenerator<T> {

    protected final int minNumber;
    protected final int maxNumber;
    protected final List<Character> characters = new ArrayList<>();

    public AbstractMathTaskGenerator(
            int minNumber,
            int maxNumber,
            EnumSet<MathTask.Operation> operations
    ) {
        this.minNumber = minNumber;
        this.maxNumber = maxNumber;
        if (operations.isEmpty()) {
            throw new IllegalArgumentException("Characters set are empty");
        }
        for (var op : operations) {
            characters.add(op.getCharacter());
        }
    }

    @Override
    public int getMaxNumber() {
        return maxNumber;
    }

    @Override
    public int getMinNumber() {
        return minNumber;
    }
}
