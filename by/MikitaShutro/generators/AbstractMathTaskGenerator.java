package by.MikitaShutro.generators;
import by.MikitaShutro.tasks.AbstractTask;
import by.MikitaShutro.Operations;
import java.util.*;

public abstract class AbstractMathTaskGenerator<T extends AbstractTask> implements MathTaskGenerator<T> {
    protected final Integer min;
    protected final Integer max;
    AbstractMathTaskGenerator(Integer mini, Integer maxi) {
        min = mini;
        max = maxi;
    }

    @Override
    public Integer getMinNumber() {
        return min;
    }

    @Override
    public Integer getMaxNumber() {
        return max;
    }

    public Integer generateIntWithinRange(Random random) {
        return random.nextInt(getDiffNumber() + 1) + min;
    }

    public Operations generateNewOperation(Random random, EnumSet<Operations> oper) {
        return oper.stream().skip(random.nextInt(oper.size()) - 1).findFirst().get();
    }
}
