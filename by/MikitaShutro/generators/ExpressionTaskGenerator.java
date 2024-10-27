package by.MikitaShutro.generators;

import by.MikitaShutro.tasks.ExpressionTask;
import by.MikitaShutro.Operations;

import java.util.*;

public class ExpressionTaskGenerator extends AbstractMathTaskGenerator<ExpressionTask> {

    final Random Random_;
    final EnumSet<Operations> Opers_;

    public ExpressionTaskGenerator(Integer max, Integer min, EnumSet<Operations> opers) {
        super(max, min);
        if (opers == null || opers.isEmpty())
            throw new NullPointerException("Операция отсутствует.");
        Opers_ = opers;
        Random_ = new Random();
    }

    @Override
    public ExpressionTask generate() {
        Integer a = generateIntWithinRange(Random_);
        Integer b = generateIntWithinRange(Random_);
        var oper = generateNewOperation(Random_, Opers_);
        return new ExpressionTask(a, b, oper);
    }
}