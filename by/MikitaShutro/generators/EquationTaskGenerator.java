package by.MikitaShutro.generators;

import by.MikitaShutro.tasks.EquationTask;
import by.MikitaShutro.Operations;

import java.util.*;

public class EquationTaskGenerator extends AbstractMathTaskGenerator<EquationTask> {

    final Random Random_;
    final EnumSet<Operations> Opers_;

    public EquationTaskGenerator(Integer max, Integer min, EnumSet<Operations> opers) {
        super(max, min);
        if (opers == null || opers.isEmpty())
            throw new NullPointerException("Операция отсутствует.");
        Opers_ = opers;
        Random_ = new Random();
    }

    @Override
    public EquationTask generate() {
        Integer a = generateIntWithinRange(Random_);
        Integer b = generateIntWithinRange(Random_);
        var oper = generateNewOperation(Random_, Opers_);
        boolean A = Random_.nextBoolean();
        return new EquationTask(a, b, oper, A);
    }
}